package com.jidu.service;

import com.jidu.pojo.local.LocalServiceStore;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/13 0013 下午 8:11
 * @Version:
 * @Description:
 */
public interface LocalServiceStoreService {
    List<LocalServiceStore> search();

    LocalServiceStore findById(Integer id);

    void add(LocalServiceStore localServiceStore);

    void update(LocalServiceStore localServiceStore);
}
