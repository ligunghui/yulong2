package com.jidu.service;

import com.jidu.entity.Result;
import com.jidu.pojo.Tree;

/**
 * @Author: liguanghui
 * Date: 2020/3/25 0025 下午 2:25
 * @Version:
 * @Description:
 */
public interface LocalServiceService {
    Result<Tree> findAll(String storeId);

    void add(String storeId, Integer[] typeIds);
}
