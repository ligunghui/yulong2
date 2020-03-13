package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.shop.ShoppingChamber;
import com.jidu.service.ChamberService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-10 10:51
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/chamber/index")
@Api(value = "商会操作", description = "商会操作")
public class ChamberController extends BusinessBaseController {
    @Autowired
    private ChamberService chamberService;

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改商会")
    public Result update(@RequestBody ShoppingChamber shoppingChamber) {
        chamberService.update(shoppingChamber);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "查询商会")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result<ShoppingChamber> findById() {
        if (storeId == null) {
            return new Result(201, "未登录", false);
        }
        Integer id = Integer.parseInt(storeId);
        ShoppingChamber shoppingChamber = chamberService.findById(id);
        return new Result(ResultCode.SUCCESS, shoppingChamber);
    }

}
