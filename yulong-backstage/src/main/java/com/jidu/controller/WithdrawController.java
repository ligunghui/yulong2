package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.pojo.withdrawal.WithdrawalApplication;
import com.jidu.service.WithdrawService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/withdraw")
@Api(value = "提现", description = "提现")
public class WithdrawController {
    @Autowired
    private WithdrawService withdrawService;
    @RequestMapping(value = "/{pageNum}/{pageSize}/{status}/{userType}", method = RequestMethod.GET)
    @ApiOperation(value = "查询提现")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path"),
            @ApiImplicitParam(name = "status", value = "0全部 1申请中2申请通过3驳回", required = true, paramType = "path"),
            @ApiImplicitParam(name = "userType", value = "用户类型(1用户2商户3商会)", required = true, paramType = "path"),
            @ApiImplicitParam(name = "param", value = "条件查询", required = false, paramType = "query")
    })
    public PageResult searchAuthentication(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @RequestParam(required = false) Map param, @PathVariable Integer status, @PathVariable Integer userType) {
        Page<WithdrawalApplication> page = PageHelper.startPage(pageNum, pageSize);
        List<WithdrawalApplication> withdrawalApplications = withdrawService.searchAuthentication(param, status,userType);
        return new PageResult(page.getTotal(), page.getResult());
    }
    @RequestMapping(value = "/delAuthentication/{id}/{status}", method = RequestMethod.GET)
    @ApiOperation(value = "处理提现")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path"),
            @ApiImplicitParam(name = "status", value = "2申请通过3申请不通过", required = true, paramType = "path")
    })
    public Result delAuthentication(@PathVariable Integer id, @PathVariable Integer status) {
        return   withdrawService.delAuthentication(id, status);
    }


}

