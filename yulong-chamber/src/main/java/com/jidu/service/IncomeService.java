package com.jidu.service;

import com.jidu.pojo.sys.UserAccount;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/9 0009 下午 5:59
 * @Version:
 * @Description:
 */
public interface IncomeService {
    List<UserAccount> findIncome(Integer type, String storeId);
}
