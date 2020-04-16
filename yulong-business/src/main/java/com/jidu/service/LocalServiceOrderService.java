package com.jidu.service;

import com.jidu.pojo.local.LocalServiceOrder;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/25 0025 下午 7:09
 * @Version:
 * @Description:
 */
public interface LocalServiceOrderService {
    void update(LocalServiceOrder localServiceOrder);

    LocalServiceOrder findById(Integer id);

    void delete(Integer id);

    List<LocalServiceOrder> search(String storeId);
}
