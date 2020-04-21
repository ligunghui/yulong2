package com.jidu.service.impl;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.mapper.OrderGoodsMapper;
import com.jidu.mapper.OrderMapper;
import com.jidu.mapper.UserAccountMapper;
import com.jidu.mapper.UserInfoMapper;
import com.jidu.pojo.order.OrderGoods;
import com.jidu.pojo.order.ShoppingOrder;
import com.jidu.pojo.sys.UserAccount;
import com.jidu.pojo.sys.UserInfo;
import com.jidu.service.OrderService;
import net.sf.saxon.event.IDFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
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
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;

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
        if (2 == state) {//允许退货,返回用户余额
            if (shoppingOrder == null) {
                return new Result(201, "订单不存在", false);
            }
            String storeId = shoppingOrder.getStoreId();//商家id 总后台是0
            if (!sId.equals(storeId)) {
                return new Result(201, "改订单不是您的订单,您无法退货", false);

            }

            String userId = shoppingOrder.getUserId();//用户id

            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
            if (userInfo == null) {
                return new Result(201, "用户为空", false);
            }
            userInfo.setWalletMoney(userInfo.getWalletMoney().add(shoppingOrder.getTotalprice()));
            userInfoMapper.updateByPrimaryKey(userInfo);
            UserAccount userAccount = new UserAccount();
            userAccount.setItemId(userId);
            userAccount.setMoney(shoppingOrder.getTotalprice());
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
