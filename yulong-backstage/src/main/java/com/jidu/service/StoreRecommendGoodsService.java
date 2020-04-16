package com.jidu.service;

import com.jidu.entity.Result;
import com.jidu.pojo.StoreGongGroup;

import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/4/4 0004 上午 10:11
 * @Version:
 * @Description:
 */
public interface StoreRecommendGoodsService {
    List<StoreGongGroup> search(Map param, Integer status, Integer type);

    Result verify(Integer id, Integer status);

    void withdraw(Integer id);
}
