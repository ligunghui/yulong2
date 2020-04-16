package com.jidu.service.impl;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.mapper.AdminRoleMapper;
import com.jidu.mapper.RoleMapper;
import com.jidu.mapper.RolePermissionMapper;
import com.jidu.pojo.Tree;
import com.jidu.pojo.TreeGroup;
import com.jidu.pojo.sys.AdminRole;
import com.jidu.pojo.sys.Role;
import com.jidu.pojo.sys.RolePermission;
import com.jidu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/4/15 0015 上午 10:37
 * @Version:
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public void add(String name, String describe, Integer[] permissionIds) {
        Role role = new Role();
        role.setDescription(describe);
        role.setName(name);
        roleMapper.insert(role);
        for (Integer permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(role.getId());
            rolePermission.setPermissionId(permissionId);
            rolePermissionMapper.insert(rolePermission);

        }
    }

    @Override
    public List<Role> search(Map param) {
        return roleMapper.selectAll();
    }

    @Override
    public void delete(Integer id) {
        deleteRolePermissionByRoleId(id);
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Integer[] permissionIds, Role role) {
        deleteRolePermissionByRoleId(role.getId());
        for (Integer permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(role.getId());
            rolePermission.setPermissionId(permissionId);
            rolePermissionMapper.insert(rolePermission);

        }
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public Role find(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addAdminRole(Integer adminId, Integer[] typeIds) {
        for (Integer typeId : typeIds) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(typeId);
            adminRoleMapper.insert(adminRole);
        }
    }

    @Override
    public Result findAll(Integer adminId) {
        TreeGroup treeGroup = new TreeGroup();
        List<Role> roles = roleMapper.selectAll();
        for (Role goodsType : roles) {
            boolean b=check(goodsType.getId(),adminId);
            Tree tree = new Tree(goodsType.getName(), goodsType.getId(), b);
            treeGroup.getData().add(tree);
        }
        return new Result(ResultCode.SUCCESS, treeGroup);
    }

    private boolean check(Integer roleId, Integer adminId) {
        Example example = new Example(AdminRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        criteria.andEqualTo("adminId", adminId);
        List<AdminRole> list = adminRoleMapper.selectByExample(example);
        return !list.isEmpty();
    }

    private void deleteRolePermissionByRoleId(Integer roleId) {
        Example example = new Example(RolePermission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        rolePermissionMapper.deleteByExample(example);
    }


}
