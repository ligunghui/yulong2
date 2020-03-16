package com.jidu.service;

import com.jidu.pojo.sys.UserAccount;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/15 0015 下午 6:55
 * @Version:
 * @Description:
 */
public interface UserAccountService {
    List<UserAccount> findUserAccount();
}
