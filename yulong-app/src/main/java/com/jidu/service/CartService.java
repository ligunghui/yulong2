package com.jidu.service;

import com.jidu.pojo.shop.ShoppingCart;

import java.util.List;

public interface CartService {
    List<ShoppingCart> findCartListFromRedis(String userId);

    List<ShoppingCart> mergeCartList(List<ShoppingCart> cartList_cookie, List<ShoppingCart> cartList_redis);

    void saveCartListToRedis(String userId, List<ShoppingCart> cartList);

    List<ShoppingCart> addGoodsToCartList(List<ShoppingCart> cartList, Long goodsId, Integer num);

}
