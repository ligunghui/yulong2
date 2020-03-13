package com.jidu.service;

import com.jidu.pojo.activity.ShoppingActivity;
import com.jidu.pojo.sys.UserInfo;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-18 15:52
 */
public interface ActivityService {
    void save(ShoppingActivity shoppingActivity);

    void update(ShoppingActivity shoppingActivity);

    ShoppingActivity findById(int id);

    void delete(int id);

    List<ShoppingActivity> search(String storeId);

    List<UserInfo> findActivityUser(String activityId);
}
