package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.goods.GiftPack;
import com.jidu.pojo.local.LocalService;
import com.jidu.pojo.sys.Role;
import com.jidu.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/4/15 0015 上午 10:33
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/role")
@Api(value = "角色", description = "角色")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "{name}/{describe}", method = RequestMethod.POST)
    @ApiOperation(value = "添加")
    @RequiresPermissions("role_add")
    public Result add(@RequestParam(value = "typeIds[]") Integer[] typeIds, @PathVariable String name, @PathVariable String describe) {
        roleService.add(name, describe, typeIds);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    @RequiresPermissions("role_find")
    public PageResult<Role> search(@PathVariable int pageNum, @PathVariable int pageSize, @RequestParam(required = false) Map param) {
        Page<Role> page = PageHelper.startPage(pageNum, pageSize);
        List<Role> roles = roleService.search(param);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除角色")
    @RequiresPermissions("role_delete")
    public Result delete(@PathVariable Integer id) {
        roleService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}/{name}/{describe}", method = RequestMethod.PUT)
    @ApiOperation(value = "修改角色")
    @RequiresPermissions("role_edit")
    public Result update(@RequestParam(value = "typeIds[]") Integer[] typeIds, @PathVariable String name, @PathVariable String describe, @PathVariable Integer id) {
        Role role = new Role();
        role.setId(id);
        role.setName(name);
        role.setDescription(describe);
        roleService.update(typeIds, role);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询")
    @RequiresPermissions("role_find")
    public Result find(@PathVariable Integer id) {
        Role role = roleService.find(id);
        return new Result(ResultCode.SUCCESS, role);
    }
    @RequestMapping(value = "{adminId}", method = RequestMethod.POST)
    @ApiOperation(value = "为管理员添加角色")
    @RequiresPermissions("add_role_to_admin")
    public Result addAdminRole(@RequestParam(value = "typeIds[]") Integer[] typeIds, @PathVariable Integer adminId) {
        roleService.addAdminRole(adminId, typeIds);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/findByAdmin/{adminId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询")
    @RequiresPermissions("add_role_to_admin")
    public Result findAll(@PathVariable Integer adminId) {
        return roleService.findAll(adminId);
    }
}
