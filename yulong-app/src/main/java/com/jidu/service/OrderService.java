package com.jidu.service;

import com.jidu.pojo.order.ShoppingOrder;

/**
 * @Author: liguanghui
 * Date: 2020/3/6 0006 下午 2:05
 * @Version:
 * @Description:
 */
public interface OrderService {
    ShoppingOrder findById(String orderId);

    void operationOrder(String orderId, Integer type);
}
