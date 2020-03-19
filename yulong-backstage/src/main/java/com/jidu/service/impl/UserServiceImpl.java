package com.jidu.service.impl;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.mapper.UserInfoMapper;
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
        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();

        return userInfoMapper.selectByExample(example);
    }

    @Override
    public void update(UserInfo userInfo) {
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserInfo> searchAuthentication(Map param, Integer authentication) {
        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("authentication", authentication);
        return userInfoMapper.selectByExample(example);
    }

    @Override
    public Result delAuthentication(String userId, Integer authentication) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if (userInfo == null) {
            return new Result(201, "用户不存在", false);
        }
        int userInfoAuthentication = userInfo.getAuthentication();
        userInfo.setAuthentication(authentication);
        if (2 == authentication && 2 != userInfoAuthentication) {//第一次实名认证
            userInfo.setVipIs(2);
        }
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
        return new Result(ResultCode.SUCCESS);
    }
}
