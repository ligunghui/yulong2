package com.jidu.service.impl;

import com.jidu.mapper.OrderMapper;
import com.jidu.pojo.order.ShoppingOrder;
import com.jidu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-06 13:59
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public void update(ShoppingOrder shoppingOrder) {
        orderMapper.updateByPrimaryKeySelective(shoppingOrder);
    }

    @Override
    public ShoppingOrder findById(String id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(String id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<ShoppingOrder> search(String storeId) {
        Example example=new Example(ShoppingOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId",storeId);
        return orderMapper.selectByExample(example);
    }


}
