package com.jidu.service;

import com.jidu.pojo.goods.ShoppingGoods;

public interface GoodsDetailsService {
    ShoppingGoods findById(long id);
}
