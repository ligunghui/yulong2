package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.pojo.sys.UserAccount;
import com.jidu.service.IncomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/25 0025 上午 11:13
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/chamber/income")
@Api(value = "商户收益", description = "商户收益")
public class IncomeController extends BusinessBaseController{
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
