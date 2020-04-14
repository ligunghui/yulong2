package com.jidu.service;

import com.jidu.pojo.shop.ShoppingGuige;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/4/14 0014 上午 9:12
 * @Version:
 * @Description:
 */
public interface ShoppingGuigeService {
    void save(List<ShoppingGuige> shoppingGuiges);

    void update(ShoppingGuige shoppingGuige);

    ShoppingGuige findById(Integer id);

    void delete(Integer id);

    List<ShoppingGuige> search(String goodsId);
}
