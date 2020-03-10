package com.jidu.service;

import com.jidu.pojo.activity.ShoppingActivity;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.opportunities.BusinessOpportunities;

import java.util.List;

public interface IndexService {
    List<ShoppingActivity> findActivityByType(String type);

    List<ShoppingGoods> findGoods();

    List<BusinessOpportunities> findOpportunities();
}
