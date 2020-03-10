package com.jidu.service.impl;

import com.jidu.mapper.ShoppingGoodsMapper;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.service.GoodsDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-23 10:49
 */
@Service
public class GoodsDetailsServiceImpl implements GoodsDetailsService {
    @Autowired
    private ShoppingGoodsMapper shoppingGoodsMapper;
    @Override
    public ShoppingGoods findById(long id) {
        return shoppingGoodsMapper.selectByPrimaryKey(id);
    }
}
