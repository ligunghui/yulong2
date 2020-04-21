package com.jidu.service;

import com.jidu.entity.Result;
import com.jidu.pojo.order.OrderGoods;
import com.jidu.pojo.order.ShoppingOrder;

import java.util.List;

public interface OrderService {
    void update(ShoppingOrder shoppingOrder);

    ShoppingOrder findById(String id);

    void delete(String id);

    List<ShoppingOrder> search(String storeId);

    List<OrderGoods> findOrderGoodsByOrderId(String orderId);

    Result returnGoods(String orderId, Integer state, String storeId);
}
