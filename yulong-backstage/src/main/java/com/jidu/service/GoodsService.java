package com.jidu.service;

import com.jidu.pojo.goods.ShoppingGoods;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    void save(ShoppingGoods shoppingGoods);

    void update(ShoppingGoods shoppingGoods);

    ShoppingGoods findById(long id);

    void delete(long id);

    List<ShoppingGoods> search(Map param);

    List<ShoppingGoods> findAll();
}
