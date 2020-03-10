package com.jidu.service;

import com.jidu.pojo.sys.AppAdvertisement;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/10 0010 下午 6:19
 * @Version:
 * @Description:
 */
public interface AppAdvertisementService {
    List<AppAdvertisement> search();

    void delete(Integer id);

    void add(AppAdvertisement appAdvertisement);
}
