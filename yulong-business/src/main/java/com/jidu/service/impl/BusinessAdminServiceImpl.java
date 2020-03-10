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
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-26 10:47
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
