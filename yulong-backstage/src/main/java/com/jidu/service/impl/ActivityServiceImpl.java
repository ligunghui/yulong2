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
        shoppingActivity.setStoreId("0");
        shoppingActivity.setStoreName("平台");
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
    public List<ShoppingActivity> search() {
        Example example = new Example(ShoppingActivity.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("addtime DESC");
        criteria.andEqualTo("storeId", "0");
        return activityMapper.selectByExample(example);
    }





}
