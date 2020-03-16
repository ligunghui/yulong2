package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.service.BusinessAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/7 0007 上午 10:46
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/chamber/admin")
@Api(value = "商会管理员操作", description = "商会管理员操作")
public class BusinessAdminController extends BusinessBaseController {
    @Autowired
    private BusinessAdminService chamberAdminService;

    @RequestMapping(value = "/updateBusinessAdmin", method = RequestMethod.PUT)
    @ApiOperation(value = "修改商会管理员用户名 密码")
    public Result updateBusinessAdmin(@RequestBody BusinessAdmin chamberAdmin) {
        chamberAdminService.updateBusinessAdmin(chamberAdmin);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/addBusinessAdmin", method = RequestMethod.POST)
    @ApiOperation(value = "添加商会管理员")
    public Result addBusinessAdmin(@RequestBody BusinessAdmin chamberAdmin) {
        List<BusinessAdmin> list= chamberAdminService.findBusinessAdminByUserName(chamberAdmin.getUsername());
        if (!list.isEmpty()){
            return new Result(201,"用户名重复",false);

        }
        chamberAdmin.setStoreId(storeId);
        chamberAdminService.addBusinessAdmin(chamberAdmin);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/findBusinessAdmin/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商会管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    public PageResult<BusinessAdmin> findBusinessAdmin(@PathVariable int pageNum, @PathVariable int pageSize) {
        Page<BusinessAdmin> page = PageHelper.startPage(pageNum, pageSize);
        List<BusinessAdmin> chamberAdmins = chamberAdminService.findBusinessAdmin(storeId);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @RequestMapping(value = "/findBusinessAdminById/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询单个商会管理员")
    public Result<BusinessAdmin> findBusinessAdminById(@PathVariable int id) {
        BusinessAdmin chamberAdmins = chamberAdminService.findBusinessAdminById(id);
        return new Result(ResultCode.SUCCESS, chamberAdmins);
    }

    @RequestMapping(value = "/deleteBusinessAdminById/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除单个商会管理员")
    public Result deleteBusinessAdminById(@PathVariable int id) {
        chamberAdminService.deleteBusinessAdminById(id);
        return new Result(ResultCode.SUCCESS);
    }
}
