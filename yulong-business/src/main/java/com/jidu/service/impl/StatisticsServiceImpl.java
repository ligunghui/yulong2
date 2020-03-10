package com.jidu.service.impl;

import com.jidu.mapper.GoodsMapper;
import com.jidu.mapper.OrderMapper;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.order.ShoppingOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-07 09:57
 */
@Service
public class StatisticsServiceImpl {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderMapper orderMapper;



    public Map<String, Object> order(String storeId, Map<String, Object> map ) {
        Example example = new Example(ShoppingOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        criteria.andNotEqualTo("orderStatus",0);
        List<ShoppingOrder> shoppingOrders = orderMapper.selectByExample(example);
        map.put("ordersTotal", shoppingOrders.size());
        return map;
    }
}
