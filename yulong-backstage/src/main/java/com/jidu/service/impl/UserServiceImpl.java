package com.jidu.service.impl;

import com.jidu.mapper.UserInfoMapper;
import com.jidu.pojo.order.ShoppingOrder;
import com.jidu.pojo.sys.UserInfo;
import com.jidu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/3/6 0006 下午 5:27
 * @Version:
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public List<UserInfo> search(Map param) {
        Example example=new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();
       if (!param.isEmpty()){


       }
        return userInfoMapper.selectByExample(example);
    }
}
