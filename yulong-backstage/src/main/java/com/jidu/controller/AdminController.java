package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/13 0013 下午 4:24
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/admin")
@Api(value = "管理员", description = "管理员")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping(value = "/updateBusinessAdmin", method = RequestMethod.PUT)
    @ApiOperation(value = "修改商户管理员用户名 密码")
    @RequiresPermissions("admin_edit")
    public Result updateBusinessAdmin(@RequestBody BusinessAdmin businessAdmin) {
        adminService.updateBusinessAdmin(businessAdmin);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/addBusinessAdmin", method = RequestMethod.POST)
    @ApiOperation(value = "添加商户管理员")
    @RequiresPermissions("admin_add")
    public Result addBusinessAdmin(@RequestBody BusinessAdmin businessAdmin) {
        businessAdmin.setStoreId("0");
        List<BusinessAdmin> list= adminService.findBusinessAdminByUserName(businessAdmin.getUsername());
        if (!list.isEmpty()){
            return new Result(201,"用户名重复",false);

        }
        adminService.addBusinessAdmin(businessAdmin);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/findBusinessAdmin/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商户管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    public PageResult<BusinessAdmin> findBusinessAdmin(@PathVariable int pageNum, @PathVariable int pageSize) {
        Page<BusinessAdmin> page = PageHelper.startPage(pageNum, pageSize);
        List<BusinessAdmin> businessAdmins= adminService.findBusinessAdmin("0");
        return new PageResult(page.getTotal(), page.getResult());
    }
    @RequestMapping(value = "/findBusinessAdminById/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询单个商户管理员")
    public Result<BusinessAdmin> findBusinessAdminById(@PathVariable int id) {
        BusinessAdmin businessAdmins= adminService.findBusinessAdminById(id);
        return new Result(ResultCode.SUCCESS,businessAdmins);
    }
    @RequestMapping(value = "/deleteBusinessAdminById/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "删除单个商户管理员")
    @RequiresPermissions("admin_delete")
    public Result deleteBusinessAdminById(@PathVariable int id) {
        adminService.deleteBusinessAdminById(id);
        return new Result(ResultCode.SUCCESS);
    }
}
