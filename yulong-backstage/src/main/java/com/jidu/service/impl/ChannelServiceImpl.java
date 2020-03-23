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

/**
 * @Author: liguanghui
 * Date: 2020/3/20下午 6:43
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
    public Result<Tree> findAll(Integer id) {
        TreeGroup treeGroup = new TreeGroup();
        Example example = new Example(GoodsType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", "0");
        criteria.andEqualTo("parentId", 1);
        List<GoodsType> goodsTypes = goodsTypeMapper.selectByExample(example);
        for (GoodsType goodsType : goodsTypes) {
            Tree tree = new Tree(goodsType.getName(), goodsType.getId(), false);
            Example example1 = new Example(GoodsType.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("parentId", goodsType.getId());
            List<GoodsType> goodsTypes2 = goodsTypeMapper.selectByExample(example1);
            for (GoodsType type : goodsTypes2) {
                boolean b = check(type.getId(), id);
                Tree tree2 = new Tree(type.getName(), type.getId(), b);
                tree.getChildren().add(tree2);
            }
            treeGroup.getData().add(tree);
        }
        return new Result(ResultCode.SUCCESS, treeGroup);
    }

    @Override
    public void add(Integer channelId, Integer[] typeIds) {
        //先删除在添加
        Example example1 = new Example(ChannelType.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("channelId", channelId);
        channelTypeMapper.deleteByExample(example1);
        for (Integer typeId : typeIds) {
            ChannelType channelType = new ChannelType();
            channelType.setChannelId(channelId);
            channelType.setTypeId(typeId);
            GoodsType goodsType = goodsTypeMapper.selectByPrimaryKey(typeId);
            if (goodsType != null) {
                channelType.setTypeName(goodsType.getName());
            }
            channelTypeMapper.insert(channelType);
        }
    }

    private boolean check(Integer typeId, Integer channelId) {
        Example example1 = new Example(ChannelType.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("typeId", typeId);
        criteria1.andEqualTo("channelId", channelId);
        List<ChannelType> list = channelTypeMapper.selectByExample(example1);
        return !list.isEmpty();
    }
}
