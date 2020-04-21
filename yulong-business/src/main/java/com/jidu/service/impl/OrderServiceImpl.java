package com.jidu.service.impl;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.mapper.*;
import com.jidu.pojo.order.OrderGoods;
import com.jidu.pojo.order.ShoppingOrder;
import com.jidu.pojo.shop.ShoppingStore;
import com.jidu.pojo.sys.UserAccount;
import com.jidu.pojo.sys.UserInfo;
import com.jidu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.Date;
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
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private StoreMapper storeMapper;

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
    public Result returnGoods(String orderId, Integer state, String sId) {
        //2申请通过3驳回
        ShoppingOrder shoppingOrder = orderMapper.selectByPrimaryKey(orderId);
        BigDecimal orderTotalPrice = shoppingOrder.getTotalprice();
        if (2 == state) {//允许退货,返回用户余额
            if (shoppingOrder == null) {
                return new Result(201, "订单不存在", false);
            }
            String storeId = shoppingOrder.getStoreId();//商家id 总后台是0
            if (!sId.equals(storeId)) {
                return new Result(201, "改订单不是您的订单,您无法退货", false);

            }
            ShoppingStore shoppingStore = storeMapper.selectByPrimaryKey(storeId);
            int a = shoppingStore.getTotalMoney().compareTo(orderTotalPrice);
            if (a == -1) {
                return new Result(201, "可用余额不足,无法退货", false);
            }
            //处理商户
            BigDecimal subtract = shoppingStore.getTotalMoney().subtract(orderTotalPrice);
            shoppingStore.setTotalMoney(subtract);
            storeMapper.updateByPrimaryKey(shoppingStore);
            UserAccount userAccount1 = new UserAccount();
            userAccount1.setItemId(storeId);
            userAccount1.setMoney(orderTotalPrice);
            userAccount1.setSymbol("-");
            userAccount1.setAction("退货");
            userAccount1.setCreateTime(new Date());
            userAccountMapper.insert(userAccount1);


            String userId = shoppingOrder.getUserId();//用户id
            //处理用户
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
            if (userInfo == null) {
                return new Result(201, "用户为空", false);
            }
            userInfo.setWalletMoney(userInfo.getWalletMoney().add(orderTotalPrice));
            userInfoMapper.updateByPrimaryKey(userInfo);
            UserAccount userAccount = new UserAccount();
            userAccount.setItemId(userId);
            userAccount.setMoney(orderTotalPrice);
            userAccount.setSymbol("+");
            userAccount.setAction("退货");
            userAccount.setCreateTime(new Date());
            userAccountMapper.insert(userAccount);
        }
        shoppingOrder.setOrderStatus(state);
        orderMapper.updateByPrimaryKeySelective(shoppingOrder);
        return new Result(ResultCode.SUCCESS);

    }


}
