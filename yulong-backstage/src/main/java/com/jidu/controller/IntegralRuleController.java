package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.sys.IntegralRule;
import com.jidu.service.IntegralRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/19 0019 下午 3:00
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/integralRule")
@Api(value = "积分规则", description = "积分规则")
public class IntegralRuleController {
    @Autowired
    private  IntegralRuleService integralRuleService;
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改积分规则")
    public Result update(@RequestBody IntegralRule integralRule) {
        integralRule.setId(1);
        integralRuleService.update(integralRule);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询积分规则")
    public Result<IntegralRule> findById(@PathVariable Integer id) {
        IntegralRule integralRule = integralRuleService.findById(id);
        return new Result(ResultCode.SUCCESS, integralRule);
    }

    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询积分规则")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    public PageResult<IntegralRule> search(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        Page<IntegralRule> page = PageHelper.startPage(pageNum, pageSize);
        List<IntegralRule> integralRule = integralRuleService.search();
        return new PageResult(page.getTotal(), page.getResult());
    }
}

