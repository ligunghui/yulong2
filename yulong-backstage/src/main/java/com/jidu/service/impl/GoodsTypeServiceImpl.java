package com.jidu.service.impl;

import com.jidu.mapper.GoodsTypeMapper;
import com.jidu.pojo.goods.GoodsType;
import com.jidu.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-25 15:22
 */
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;
    @Override
    public void save(String id, GoodsType goodsType) {
        goodsType.setStoreId(id);
        goodsTypeMapper.insert(goodsType);
    }

    @Override
    public void update(GoodsType goodsType) {
        goodsTypeMapper.updateByPrimaryKey(goodsType);
    }

    @Override
    public GoodsType findById(int id) {
        return goodsTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(int id) {

        goodsTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<GoodsType> search(Map param) {
        Example example=new Example(GoodsType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId",0);
        criteria.andEqualTo("parentId",1);
        return goodsTypeMapper.selectByExample(example);
    }

    @Override
    public List<GoodsType> findOneLevel(String storeId,Integer parentId) {
        Example example=new Example(GoodsType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId",storeId);
        criteria.andEqualTo("parentId",parentId);
        return goodsTypeMapper.selectByExample(example);
    }
}
