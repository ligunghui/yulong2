package com.jidu.service;

import com.jidu.entity.Result;
import com.jidu.pojo.withdrawal.WithdrawalApplication;

import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/3/20 0020 下午 3:48
 * @Version:
 * @Description:
 */
public interface WithdrawService {
    List<WithdrawalApplication> searchAuthentication(Map param, Integer status, Integer userType);

    Result delAuthentication(Integer id, Integer status);
}
