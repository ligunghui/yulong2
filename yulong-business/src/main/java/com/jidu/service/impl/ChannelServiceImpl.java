package com.jidu.service.impl;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.mapper.ChannelTypeMapper;
import com.jidu.mapper.GoodsTypeMapper;
import com.jidu.pojo.Tree;
import com.jidu.pojo.TreeGroup;
import com.jidu.pojo.goods.ChannelType;
import com.jidu.pojo.goods.GoodsType;
import com.jidu.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/3/23 0023 下午 6:24
 * @Version:
 * @Description:
 */
@Service
public class ChannelServiceImpl implements ChannelService {
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;
    @Autowired
    private ChannelTypeMapper channelTypeMapper;
    @Override
    public Result<Tree> findAll(String storeId) {
        TreeGroup treeGroup = new TreeGroup();
        Example example = new Example(GoodsType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", "0");
        criteria.andEqualTo("parentId", 1);
        List<GoodsType> goodsTypes = goodsTypeMapper.selectByExample(example);
        for (GoodsType goodsType : goodsTypes) {//所有的一级分类
            Tree tree = new Tree(goodsType.getName(), goodsType.getId(), false);
            Example example1 = new Example(GoodsType.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("parentId", goodsType.getId());
            List<GoodsType> goodsTypes2 = goodsTypeMapper.selectByExample(example1);//所有的二级分类
            for (GoodsType type : goodsTypes2) {
                boolean b = check(type.getName(), storeId);
                Tree tree2 = new Tree(type.getName(), type.getId(), b);
                tree.getChildren().add(tree2);
            }
            treeGroup.getData().add(tree);
        }
        return new Result(ResultCode.SUCCESS, treeGroup);
    }

    @Override
    public void add(String storeId, Map<Integer, List<Integer>> map) {
        Example example = new Example(GoodsType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        goodsTypeMapper.deleteByExample(example);
        for (int key : map.keySet()) {
            GoodsType goodsType1 = goodsTypeMapper.selectByPrimaryKey(key);
            if (goodsType1!=null){
                GoodsType goodsType=new GoodsType();
                goodsType.setName(goodsType1.getName());
                goodsType.setParentId(1);
                goodsType.setStoreId(storeId);
                goodsTypeMapper.insert(goodsType);
                List<Integer> integers = map.get(key);
                for (Integer integer : integers) {
                    GoodsType goodsType3 = goodsTypeMapper.selectByPrimaryKey(integer);
                    if (goodsType3!=null){
                        GoodsType goodsType2=new GoodsType();
                        goodsType2.setName(goodsType3.getName());
                        goodsType2.setParentId(goodsType.getId());
                        goodsType2.setStoreId(storeId);
                        goodsTypeMapper.insert(goodsType2);
                    }
                }
            }
        }
    }


    private boolean check(String typeName, String storeId) {
        Example example1 = new Example(GoodsType.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("name", typeName);
        criteria1.andEqualTo("storeId", storeId);
        List<GoodsType> list = goodsTypeMapper.selectByExample(example1);
        return !list.isEmpty();
    }
}
