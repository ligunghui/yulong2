package com.jidu.service;

import com.jidu.pojo.order.ShoppingOrder;

import java.util.List;

public interface OrderService {
    void update(ShoppingOrder shoppingOrder);

    ShoppingOrder findById(String id);

    void delete(String id);

    List<ShoppingOrder> search(String storeId);

}
