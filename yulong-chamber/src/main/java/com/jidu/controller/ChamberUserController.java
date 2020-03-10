package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.shop.ChamberUser;
import com.jidu.pojo.shop.ShoppingChamber;
import com.jidu.service.ChamberService;
import com.jidu.service.ChamberUserService;
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
 * @create: 2020-02-11 21:01
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/chamber/user")
@Api(value = "商会用户操作", description = "商会用户操作")

public class ChamberUserController extends BusinessBaseController {
    @Autowired
    private ChamberUserService chamberUserService;

    @RequestMapping(value = "/findChamberUserByChamberId", method = RequestMethod.GET)
    @ApiOperation(value = "商会成员")
    public List<ChamberUser> findChamberUserByChamberId() {
        Integer chamberId = Integer.parseInt(storeId);
        List<ChamberUser> chamberUser = chamberUserService.findChamberUserByChamberId(chamberId);
        return chamberUser;
    }

    @RequestMapping(value = "/delChamberUser/{id}/{status}", method = RequestMethod.GET)
    @ApiOperation(value = "处理商会成员申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path"),
            @ApiImplicitParam(name = "status", value = "1审核通过2审核不通过", required = true, paramType = "path"),
            @ApiImplicitParam(name = "violationReseaon", value = "拒绝理由", required = false, paramType = "query")
    })
    public Result delChamberUser(@PathVariable Integer id, @PathVariable Integer status, @RequestParam(required = false) String violationReseaon) {
         chamberUserService.delChamberUser(id,status,violationReseaon,userName);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/findChamberUserById/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商会成员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path"),
    })
    public Result findChamberUserById(@PathVariable Integer id) {
        ChamberUser chamberUser= chamberUserService.findChamberUserById(id);
        return new Result(ResultCode.SUCCESS,chamberUser);
    }

}
