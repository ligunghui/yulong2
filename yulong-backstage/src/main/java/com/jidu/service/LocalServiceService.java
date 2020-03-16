package com.jidu.service;

import com.jidu.pojo.local.LocalService;
import com.jidu.pojo.local.LocalServiceStore;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/13 0013 下午 7:38
 * @Version:
 * @Description:
 */
public interface LocalServiceService {
    void update(LocalService aboutUs);

    void add(LocalService localService);

    LocalService findById(Integer id);

    List<LocalService> search();

    List<LocalServiceStore> findByServiceId(Integer serviceId);

    void delete(Integer id);
}
