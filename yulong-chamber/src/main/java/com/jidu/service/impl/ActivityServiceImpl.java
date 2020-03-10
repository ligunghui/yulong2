package com.jidu.service.impl;

import com.jidu.mapper.ActivityMapper;
import com.jidu.pojo.activity.ShoppingActivity;
import com.jidu.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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

    @Override
    public void save(ShoppingActivity shoppingActivity) {
        activityMapper.insert(shoppingActivity);
    }

    @Override
    public void update(ShoppingActivity shoppingActivity) {
        activityMapper.updateByPrimaryKey(shoppingActivity);
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


}
