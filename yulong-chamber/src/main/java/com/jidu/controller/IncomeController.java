package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.shop.ShoppingStore;
import com.jidu.pojo.sys.UserAccount;
import com.jidu.service.IncomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-25 14:56
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/chamber/income")
@Api(value = "商会收益", description = "商会收益")
public class IncomeController extends  BusinessBaseController{
    @Autowired
    private IncomeService incomeService;
    @RequestMapping(value = "/{type}/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商会收益")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path"),
            @ApiImplicitParam(name = "type", value = "1本月2历史", required = true, paramType = "path")
    })
    public PageResult<UserAccount> findIncome(@PathVariable Integer type, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        Page<UserAccount> page = PageHelper.startPage(pageNum, pageSize);
        List<UserAccount> userAccountList=incomeService.findIncome(type,storeId);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
