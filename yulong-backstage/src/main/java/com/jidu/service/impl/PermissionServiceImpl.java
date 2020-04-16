package com.jidu.service.impl;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.mapper.PermissionMapper;
import com.jidu.mapper.RolePermissionMapper;
import com.jidu.pojo.Tree;
import com.jidu.pojo.TreeGroup;
import com.jidu.pojo.goods.ChannelType;
import com.jidu.pojo.sys.Permission;
import com.jidu.pojo.sys.RolePermission;
import com.jidu.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/13 0013 下午 6:31
 * @Version:
 * @Description:
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Result<Tree> search(Integer roleId) {
        TreeGroup treeGroup = new TreeGroup();
        Example example = new Example(Permission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", 0);
        List<Permission> permissions = permissionMapper.selectByExample(example);
        for (Permission permission : permissions) {
            Tree tree = new Tree(permission.getName(), permission.getId(), false);
            Example example1 = new Example(Permission.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("parentId", permission.getId());
            List<Permission> permissions1 = permissionMapper.selectByExample(example1);
            for (Permission type : permissions1) {
                boolean b = false;
                if (0 != roleId) {

                    b = check(type.getId(), roleId);
                }
                Tree tree2 = new Tree(type.getName(), type.getId(), b);
                tree.getChildren().add(tree2);
            }
            treeGroup.getData().add(tree);
        }
        return new Result(ResultCode.SUCCESS, treeGroup);
    }

    private boolean check(Integer permissionId, Integer roleId) {
        Example example1 = new Example(RolePermission.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("roleId", roleId);
        criteria1.andEqualTo("permissionId", permissionId);
        List<RolePermission> list = rolePermissionMapper.selectByExample(example1);
        return !list.isEmpty();
    }
}
