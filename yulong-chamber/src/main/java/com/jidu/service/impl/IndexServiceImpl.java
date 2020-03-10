package com.jidu.service.impl;

import com.jidu.group.IndexGroup;
import com.jidu.service.IncomeService;
import com.jidu.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liguanghui
 * Date: 2020/3/9 0009 下午 7:03
 * @Version:
 * @Description:
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IncomeService incomeService;
    @Override
    public IndexGroup findAll(String storeId) {
        incomeService.findIncome(1,storeId);
        return null;
    }
}
