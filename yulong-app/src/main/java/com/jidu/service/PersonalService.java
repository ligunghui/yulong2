package com.jidu.service;

import com.jidu.entity.Result;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.order.ShoppingOrder;
import com.jidu.pojo.shop.ChamberUser;
import com.jidu.pojo.shop.ShoppingStore;

import java.util.List;

public interface PersonalService {
    List<ShoppingOrder> findOrderByUserId(String orderId, int status);

    Result UserApplyChamber(ChamberUser chamberUser);

    Result findChamberByUserId(String userId);

    Result cancelChamberByUserId(String userId);

    List<ShoppingGoods> findCollectByUserId(String userId);

    Result UserApplyBusiness(ShoppingStore shoppingStore);
}
