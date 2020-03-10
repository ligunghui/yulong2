package com.jidu.service.impl;

import com.jidu.mapper.UserAccountMapper;
import com.jidu.pojo.sys.UserAccount;
import com.jidu.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/9 0009 下午 6:00
 * @Version:
 * @Description:
 */
@Service
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    public List<UserAccount> findIncome(Integer type, String storeId) {
        //1今日2历史

        Example example = new Example(UserAccount.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", storeId);
        example.setOrderByClause("create_time desc");
        if (1 == type) {
            criteria.andBetween("createTime", getTimesMonthMorning(), new Date());
        }
        return userAccountMapper.selectByExample(example);
    }

    public static Date getTimesMonthMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }
}
