package com.jidu.service;

import com.jidu.entity.Result;
import com.jidu.pojo.StoreGongGroup;

import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/4/4 0004 上午 11:23
 * @Version:
 * @Description:
 */
public interface StoreRecommendGoodsService {
    List<StoreGongGroup> search(Map param, int status, Integer type, String storeId);

    void withdraw(Integer id);


    Result add(Long goodsId, Integer type, String storeId);
}
