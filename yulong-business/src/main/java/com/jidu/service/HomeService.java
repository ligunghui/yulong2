package com.jidu.service;

import com.jidu.pojo.order.StatisticsGroup;

import java.text.ParseException;

/**
 * @Author: liguanghui
 * Date: 2020/3/10 0010 下午 3:28
 * @Version:
 * @Description:
 */
public interface HomeService {
    StatisticsGroup index(String storeId) throws ParseException;
}
