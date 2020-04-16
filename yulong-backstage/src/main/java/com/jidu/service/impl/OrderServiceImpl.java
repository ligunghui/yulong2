package com.jidu.service.impl;

import com.jidu.mapper.OrderGoodsMapper;
import com.jidu.mapper.OrderMapper;
import com.jidu.pojo.order.OrderGoods;
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
 * @create: 2020-02-25 15:56
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

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
        Example example = new Example(ShoppingOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        return orderMapper.selectByExample(example);
    }

    @Override
    public List<OrderGoods> findOrderGoodsByOrderId(String orderId) {
        Example example = new Example(OrderGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", orderId);
        return orderGoodsMapper.selectByExample(example);
    }

    @Override
    public void returnGoods(String orderId, Integer state) {
          if (2==state){//允许退货,返回用户余额
              orderMapper.selectByPrimaryKey(orderId);
          }
    }

}
