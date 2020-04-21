package com.jidu.controller;


import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.Tree;

import com.jidu.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-27 17:22
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/permission")
@Api(value = "权限", description = "权限")
public class PermissionController extends BusinessBaseController{
    @Autowired
    private PermissionService permissionService;
    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
    @ApiOperation(value = "通过角色id查询所分配的权限")
    public  Result<Tree>  search(@PathVariable Integer roleId) {
        return new Result(ResultCode.SUCCESS,permissionService.search(roleId));
    }
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "")
    public  Result  findPermission() {

        return new Result(ResultCode.SUCCESS,permsName);
    }


}
