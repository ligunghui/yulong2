package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.activity.ShoppingActivity;
import com.jidu.pojo.shop.ShoppingChamber;
import com.jidu.service.ChamberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/4/20 0020 下午 3:23
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/business/chamber")
@Api(value = "商会", description = "商会")
public class ChamberController extends BusinessBaseController{
    @Autowired
    private ChamberService chamberService;
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询商会")
    public Result<ShoppingChamber> search() {
        List<ShoppingChamber> shoppingChambers = chamberService.search();
        return new Result(ResultCode.SUCCESS, shoppingChambers);
    }

}
