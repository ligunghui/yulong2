package com.jidu.service.impl;

import com.jidu.mapper.ActivityMapper;
import com.jidu.mapper.ActivityUserMapper;
import com.jidu.mapper.UserInfoMapper;
import com.jidu.pojo.activity.ActivityUser;
import com.jidu.pojo.activity.ShoppingActivity;
import com.jidu.pojo.sys.UserInfo;
import com.jidu.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-18 15:52
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityUserMapper activityUserMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void save(ShoppingActivity shoppingActivity) {
        shoppingActivity.setAddtime(new Date());
        activityMapper.insert(shoppingActivity);
    }

    @Override
    public void update(ShoppingActivity shoppingActivity) {
        activityMapper.updateByPrimaryKeySelective(shoppingActivity);
    }

    @Override
    public ShoppingActivity findById(int id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(int id) {
        activityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<ShoppingActivity> search(String storeId) {
        Example example = new Example(ShoppingActivity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        example.setOrderByClause("addtime DESC");
        return activityMapper.selectByExample(example);
    }

    @Override
    public List<UserInfo> findActivityUser(String activityId) {
        List<UserInfo> list = new ArrayList<>();
        Example example = new Example(ActivityUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("activityId", activityId);
        List<ActivityUser> activityUsers = activityUserMapper.selectByExample(example);
        for (ActivityUser activityUser : activityUsers) {
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(activityUser.getUserId());
            list.add(userInfo);
        }
        return list;
    }

}
