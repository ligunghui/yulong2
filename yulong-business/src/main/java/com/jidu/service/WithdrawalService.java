package com.jidu.service;

import com.jidu.entity.Result;
import com.jidu.pojo.withdrawal.WithdrawalApplication;

import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/3/20 0020 上午 11:16
 * @Version:
 * @Description:
 */
public interface WithdrawalService {
    void save(WithdrawalApplication withdrawalApplication);

    Result delete(Integer id);

    List<WithdrawalApplication> search(Map param, String storeId);
}
