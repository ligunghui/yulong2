package com.jidu.service.impl;

import com.jidu.mapper.AdminRoleMapper;
import com.jidu.mapper.BusinessAdminMapper;
import com.jidu.mapper.PermissionMapper;
import com.jidu.mapper.RolePermissionMapper;
import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.pojo.sys.AdminRole;
import com.jidu.pojo.sys.Permission;
import com.jidu.pojo.sys.RolePermission;
import com.jidu.service.BusinessAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
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
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

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
        //查找所属的角色
        Example example = new Example(AdminRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("adminId", businessAdmin.getId());
        List<AdminRole> adminRoles = adminRoleMapper.selectByExample(example);


        return findPermissionByAdminRole(adminRoles);
    }

    private List<Permission> findPermissionByAdminRole(List<AdminRole> adminRoles) {
        List<Permission> permissions = new ArrayList<>();
        for (AdminRole adminRole : adminRoles) {
            Example example = new Example(RolePermission.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("roleId", adminRole.getRoleId());
            List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(example);
            for (RolePermission rolePermission : rolePermissions) {
                Permission permission = permissionMapper.selectByPrimaryKey(rolePermission.getPermissionId());
                if (permission != null) {
                    permissions.add(permission);
                }
            }
        }

        return permissions;
    }
}

