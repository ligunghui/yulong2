package com.jidu.service.impl;

import com.jidu.mapper.UserAccountMapper;
import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.pojo.sys.UserAccount;
import com.jidu.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/15 0015 下午 6:55
 * @Version:
 * @Description:
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Override
    public List<UserAccount> findUserAccount() {
        Example example = new Example(UserAccount.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", "0");
        return userAccountMapper.selectByExample(example);
    }
}
