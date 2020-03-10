package com.jidu.service;

import com.jidu.pojo.sys.IncomeSetting;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/9 0009 下午 2:15
 * @Version:
 * @Description:
 */
public interface IncomeSettingService {
    void update(IncomeSetting incomeSetting);

    IncomeSetting findById(Integer id);

    List<IncomeSetting> search();
}
