package com.jidu.service;

import com.jidu.group.IndexGroup;

/**
 * @Author: liguanghui
 * Date: 2020/3/9 0009 下午 7:03
 * @Version:
 * @Description:
 */
public interface IndexService {
    IndexGroup findAll(String storeId);
}
