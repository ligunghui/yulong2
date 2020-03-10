package com.jidu.service.impl;

import com.jidu.mapper.IncomeSettingMapper;
import com.jidu.pojo.sys.IncomeSetting;
import com.jidu.service.IncomeSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/9 0009 下午 2:15
 * @Version:
 * @Description:
 */
@Service
public class IncomeSettingServiceImpl implements IncomeSettingService {
    @Autowired
    private IncomeSettingMapper incomeSettingMapper;

    @Override
    public void update(IncomeSetting incomeSetting) {
        incomeSettingMapper.updateByPrimaryKeySelective(incomeSetting);
    }

    @Override
    public IncomeSetting findById(Integer id) {
        return incomeSettingMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<IncomeSetting> search() {
        return incomeSettingMapper.selectAll();
    }
}
