package com.jidu.service.impl;

import com.jidu.mapper.ShoppingOrderMapper;
import com.jidu.pojo.order.ShoppingOrder;
import com.jidu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liguanghui
 * Date: 2020/3/6 0006 下午 2:07
 * @Version:
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ShoppingOrderMapper shoppingOrderMapper;

    @Override
    public ShoppingOrder findById(String orderId) {
        return shoppingOrderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public void operationOrder(String orderId, Integer type) {
        ShoppingOrder shoppingOrder = shoppingOrderMapper.selectByPrimaryKey(orderId);
        shoppingOrder.setOrderStatus(type);
        shoppingOrderMapper.updateByPrimaryKeySelective(shoppingOrder);
    }
}
