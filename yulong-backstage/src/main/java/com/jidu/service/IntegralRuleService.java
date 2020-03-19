package com.jidu.service;

import com.jidu.pojo.sys.IntegralRule;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/19 0019 下午 3:13
 * @Version:
 * @Description:
 */
public interface IntegralRuleService {
    void update(IntegralRule integralRule);

    IntegralRule findById(Integer id);

    List<IntegralRule> search();
}
