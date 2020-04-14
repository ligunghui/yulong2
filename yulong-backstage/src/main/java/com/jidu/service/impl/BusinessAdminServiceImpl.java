package com.jidu.service.impl;

import com.jidu.mapper.BusinessAdminMapper;
import com.jidu.mapper.PermissionMapper;
import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.pojo.sys.Permission;
import com.jidu.service.BusinessAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/12 0012 下午 5:04
 * @Version:
 * @Description:
 */
@Service
public class BusinessAdminServiceImpl implements BusinessAdminService {
    @Autowired
    private BusinessAdminMapper businessAdminMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public BusinessAdmin findByUserName(String mobile) {
        BusinessAdmin businessAdmin = new BusinessAdmin();
        businessAdmin.setUsername(mobile);
        businessAdmin.setType(0);
        businessAdmin.setUseable(1);
        return businessAdminMapper.selectOne(businessAdmin);
    }

    @Override
    public List<Permission> findPermission(BusinessAdmin businessAdmin) {
        Example example = new Example(Permission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("adminId", businessAdmin.getId());
        return permissionMapper.selectByExample(example);
    }
}

