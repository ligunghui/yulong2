package com.jidu.service;

import com.jidu.entity.Result;
import com.jidu.pojo.Tree;

import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/3/23 0023 下午 6:24
 * @Version:
 * @Description:
 */
public interface ChannelService {
    Result<Tree> findAll(String storeId);

    void add(String storeId, Map<Integer, List<Integer>> map);
}
