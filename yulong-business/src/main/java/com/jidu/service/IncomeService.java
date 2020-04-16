package com.jidu.service;

import com.jidu.pojo.sys.UserAccount;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/25 0025 上午 11:14
 * @Version:
 * @Description:
 */
public interface IncomeService {
    List<UserAccount> findIncome(Integer type, String storeId);
}
