package com.jidu.service;

import com.jidu.entity.Result;
import com.jidu.pojo.sys.Role;

import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/4/15 0015 上午 10:36
 * @Version:
 * @Description:
 */
public interface RoleService {
    void add(String name, String describe, Integer[] typeIds);

    List<Role> search(Map param);

    void delete(Integer id);

    void update(Integer[] typeIds, Role role);

    Role find(Integer id);

    void addAdminRole(Integer adminId, Integer[] typeIds);

    Result findAll(Integer adminId);
}
