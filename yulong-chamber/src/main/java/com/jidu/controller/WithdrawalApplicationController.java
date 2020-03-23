package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.shop.ShoppingChamber;
import com.jidu.pojo.shop.ShoppingStore;
import com.jidu.pojo.withdrawal.WithdrawalApplication;
import com.jidu.service.ChamberService;
import com.jidu.service.WithdrawalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/3/20 0020 上午 11:53
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/chamber/withdrawal")
@Api(value = "提现", description = "提现")
public class WithdrawalApplicationController extends BusinessBaseController{
    @Autowired
    private WithdrawalService withdrawalService;
    @Autowired
    private ChamberService chamberService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "申请提现")
    public Result save(@RequestBody WithdrawalApplication withdrawalApplication) {
        ShoppingChamber shoppingChamber = chamberService.findById(Integer.parseInt(storeId));
        if (shoppingChamber.getTotalMoney()==null){
            return new Result(201,"可用余额不足",false);
        }
        int a = shoppingChamber.getTotalMoney().compareTo(withdrawalApplication.getMoney());
        if (a==-1){
            return new Result(201,"可用余额不足",false);
        }
        withdrawalApplication.setUid(storeId);
        withdrawalApplication.setUserName(userName);
        withdrawalApplication.setUserType(3);
        withdrawalService.save(withdrawalApplication);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除提现")
    public Result delete(@PathVariable Integer id) {

        return withdrawalService.delete(id);
    }

    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询提现")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    public PageResult<WithdrawalApplication> search(@PathVariable int pageNum, @PathVariable int pageSize, @RequestParam(required = false) Map param) {
        Page<WithdrawalApplication> page = PageHelper.startPage(pageNum, pageSize);
        List<WithdrawalApplication> withdrawalApplications = withdrawalService.search(param, storeId);
        return new PageResult(page.getTotal(), page.getResult());
    }
}