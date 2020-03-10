package com.jidu.service;

import com.jidu.entity.Result;
import com.jidu.group.ConfirmOrder;
import com.jidu.pojo.order.ShoppingOrder;

public interface PayService {
    void payok(String out_trade_no, String type, String payType);

    ShoppingOrder getOrderById(String orderId);

    String payBack(String resXml);

    Result wxPay(int total_fee, String out_trade_no, String type);

    ConfirmOrder confirmOrder(String goodsId, Integer num, String userId);
}
