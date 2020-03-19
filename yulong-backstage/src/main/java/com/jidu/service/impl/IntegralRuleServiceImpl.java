package com.jidu.service.impl;

import com.jidu.mapper.IntegralRuleMapper;
import com.jidu.pojo.sys.IntegralRule;
import com.jidu.service.IntegralRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/19 0019 下午 3:15
 * @Version:
 * @Description:
 */
@Service
public class IntegralRuleServiceImpl implements IntegralRuleService {
    @Autowired
    private IntegralRuleMapper integralRuleMapper;

    @Override
    public void update(IntegralRule integralRule) {
        integralRuleMapper.updateByPrimaryKeySelective(integralRule);
    }

    @Override
    public IntegralRule findById(Integer id) {
        return integralRuleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<IntegralRule> search() {
        return integralRuleMapper.selectAll();
    }
}
