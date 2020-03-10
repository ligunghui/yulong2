package com.jidu.service;

import com.jidu.pojo.StatisticsGroup;

import java.text.ParseException;

/**
 * @Author: liguanghui
 * Date: 2020/3/4 0004 下午 2:23
 * @Version:
 * @Description:
 */
public interface HomeService {
    StatisticsGroup index(String storeId)throws ParseException;
}
