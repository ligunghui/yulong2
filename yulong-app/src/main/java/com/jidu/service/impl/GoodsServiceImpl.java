package com.jidu.service.impl;

import com.jidu.mapper.GoodsTypeMapper;
import com.jidu.mapper.ShoppingGoodsMapper;
import com.jidu.pojo.goods.GoodsType;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-20 19:33
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private ShoppingGoodsMapper shoppingGoodsMapper;
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public List<GoodsType> findGoodsTypeByStoreId(String storeId) {
        Example example=new Example(GoodsType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId",storeId);
        return goodsTypeMapper.selectByExample(example);
    }

    @Override
    public List<ShoppingGoods> findGoodsByStoreIdAndType(String storeId, Integer typeId) {
        Example example = new Example(ShoppingGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        if (0 != typeId) {

            criteria.andEqualTo("typeId", typeId);
        }
        return shoppingGoodsMapper.selectByExample(example);
    }
}
