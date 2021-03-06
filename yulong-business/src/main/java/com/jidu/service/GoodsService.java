package com.jidu.service;

import com.jidu.entity.Result;
import com.jidu.pojo.goods.ShoppingGoods;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    void save(ShoppingGoods shoppingGoods);

    void update(ShoppingGoods shoppingGoods);

    ShoppingGoods findById(long id);

    Result delete(long id);

    List<ShoppingGoods> search(Map param, String storeId);
}
