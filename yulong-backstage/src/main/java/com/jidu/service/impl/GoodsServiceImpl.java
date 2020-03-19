package com.jidu.service.impl;

import com.jidu.mapper.GoodsMapper;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-05 14:50
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void save(ShoppingGoods shoppingGoods) {
        shoppingGoods.setAddtime(new Date());
        shoppingGoods.setStoreId("0");
        shoppingGoods.setStoreName("平台");
        shoppingGoods.setSurplusNum(shoppingGoods.getTotalNum());
        shoppingGoods.setSaleNum(0);
        shoppingGoods.setDeletestatus(false);
        //shoppingGoods.setOnsale(1);
        goodsMapper.insert(shoppingGoods);
    }

    @Override
    public void update(ShoppingGoods shoppingGoods) {
        goodsMapper.updateByPrimaryKeySelective(shoppingGoods);
    }

    @Override
    public ShoppingGoods findById(long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(long id) {
        ShoppingGoods shoppingGoods = new ShoppingGoods();
        shoppingGoods.setId(id);
        shoppingGoods.setDeletestatus(true);
        goodsMapper.updateByPrimaryKeySelective(shoppingGoods);
    }

    @Override
    public List<ShoppingGoods> search(Map param) {
        Example example = new Example(ShoppingGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", "0");
        criteria.andEqualTo("deletestatus", false);
        return goodsMapper.selectByExample(example);
    }
}
