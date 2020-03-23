package com.jidu.service;

import com.jidu.entity.Result;
import com.jidu.pojo.Tree;

/**
 * @Author: liguanghui
 * Date: 2020/3/20 0020 下午 6:42
 * @Version:
 * @Description:
 */
public interface ChannelService {
    Result<Tree> findAll(Integer id);

    void add(Integer id, Integer[] typeIds);
}
