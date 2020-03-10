package com.jidu.service.impl;

import com.jidu.mapper.GoodsMapper;
import com.jidu.mapper.OrderMapper;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.order.SevenOrder;
import com.jidu.pojo.order.ShoppingOrder;
import com.jidu.pojo.order.StatisticsGroup;
import com.jidu.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: liguanghui
 * Date: 2020/3/10 0010 下午 3:28
 * @Version:
 * @Description:
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public StatisticsGroup index(String storeId) throws ParseException {
        StatisticsGroup statisticsGroup = new StatisticsGroup();
        // 1.最近七天下单趋势
        List<SevenOrder> sevenOrders = getsevenOrders(storeId);
        statisticsGroup.setSevenOrders(sevenOrders);
        // 3.本日订单数
        int value = sevenOrders.get(6).getValue();
        statisticsGroup.setNowOrders(value);
        // 4.未处理订单数
        Example example = new Example(ShoppingOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        List<ShoppingOrder> orders = orderMapper.selectByExample(example);
        int shipped = 0;
        int returnGoods = 0;
        for (ShoppingOrder order : orders) {
            if ("1".equals(order.getOrderStatus())) {//未发货
                shipped++;
            }
            if ("4".equals(order.getOrderStatus())) {//申请退货
                returnGoods++;
            }

        }
        statisticsGroup.setShipped(shipped);
        statisticsGroup.setReturnGoods(returnGoods);
        return product(storeId, statisticsGroup);
    }

    private List<SevenOrder> getsevenOrders(String storeId) throws ParseException {
        String[] beforeSevenDay = getBeforeSevenDay();
        List<SevenOrder> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        for (String string : beforeSevenDay) {
            SevenOrder sevenOrder = new SevenOrder();
            Example example = new Example(ShoppingOrder.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("storeId", storeId);
            Date statTime = sdf.parse(string + " 00:00:00");
            Date endTime = sdf.parse(string + " 23:59:59");
            criteria.andBetween("paytime", statTime, endTime);
            int i = orderMapper.selectCountByExample(example);
            sevenOrder.setName(string);
            sevenOrder.setValue(i);
            list.add(sevenOrder);
        }

        return list;
    }


    private String[] getBeforeSevenDay() {
        String[] arr = new String[7];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = null;
        for (int i = 0; i < 7; i++) {
            c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, -i);
            arr[6 - i] = sdf.format(c.getTime());
        }
        return arr;
    }

    //统计产品
    public StatisticsGroup product(String storeId, StatisticsGroup statisticsGroup) {
        Map<String, Object> map = new HashMap<>();
        Example example = new Example(ShoppingGoods.class);
        example.setOrderByClause("sale_num DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        int lessGoods = 0;
        int newGoods = 0;
        List<ShoppingGoods> shoppingGoods = goodsMapper.selectByExample(example);
        for (ShoppingGoods shoppingGood : shoppingGoods) {

            if (shoppingGood.getSurplusNum() < 5) {
                lessGoods++;
            }
            if (shoppingGood.getIsNew() == 1) {
                newGoods++;
            }
        }
        statisticsGroup.setGoodsTotal(shoppingGoods.size());
        statisticsGroup.setNewGoods(newGoods);
        statisticsGroup.setLessGoods(lessGoods);

        return statisticsGroup;
    }
}


