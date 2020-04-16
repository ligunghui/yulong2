package com.jidu.service;

import com.jidu.pojo.order.OrderGoods;
import com.jidu.pojo.order.ShoppingOrder;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-25 15:58
 */
public interface OrderService {

    void update(ShoppingOrder shoppingOrder);

    ShoppingOrder findById(String id);

    void delete(String id);

    List<ShoppingOrder> search(String storeId);

    List<OrderGoods> findOrderGoodsByOrderId(String orderId);

    void returnGoods(String orderId, Integer state);
}
