package com.jidu.service;

import com.jidu.entity.Result;
import com.jidu.pojo.Tree;

/**
 * @Author: liguanghui
 * Date: 2020/3/13 0013 下午 6:31
 * @Version:
 * @Description:
 */
public interface PermissionService {
    Result<Tree> search(Integer roleId);
}
