package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.pojo.sys.AboutUs;
import com.jidu.pojo.sys.Permission;
import com.jidu.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    public PageResult<Permission> search(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        Page<Permission> page = PageHelper.startPage(pageNum, pageSize);
        List<Permission> aboutUs = permissionService.search();
        return new PageResult(page.getTotal(), page.getResult());
    }
}
