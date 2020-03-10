package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.sys.AboutUs;
import com.jidu.pojo.sys.IncomeSetting;
import com.jidu.service.IncomeSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/9 0009 下午 2:12
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/incomeSetting")
@Api(value = "收益设置", description = "收益设置")
public class IncomeSettingController {
    @Autowired
    private IncomeSettingService incomeSettingService;
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改收益设置")
    public Result update(@RequestBody IncomeSetting incomeSetting) {
        incomeSetting.setId(1);
        incomeSettingService.update(incomeSetting);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询收益设置")
    public Result<IncomeSetting> findById(@PathVariable Integer id) {
        IncomeSetting incomeSetting = incomeSettingService.findById(id);
        return new Result(ResultCode.SUCCESS, incomeSetting);
    }

    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询收益设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    public PageResult<IncomeSetting> search(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        Page<IncomeSetting> page = PageHelper.startPage(pageNum, pageSize);
        List<IncomeSetting> incomeSettings = incomeSettingService.search();
        return new PageResult(page.getTotal(), page.getResult());
    }
}