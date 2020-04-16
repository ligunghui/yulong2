package com.jidu.service;

import com.jidu.pojo.local.LocalService;
import com.jidu.pojo.local.LocalServiceGoods;

import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/3/25 0025 下午 2:02
 * @Version:
 * @Description:
 */
public interface LocalServiceGoodsService {
    void save(LocalServiceGoods localServiceGoods);

    void update(LocalServiceGoods localServiceGoods);

    LocalServiceGoods findById(Long id);

    void delete(Long id);

    List<LocalServiceGoods> search(Map param, String storeId);

    List<LocalService> findService(String storeId);
}
