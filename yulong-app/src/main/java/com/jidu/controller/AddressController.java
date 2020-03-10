package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.address.ShoppingAddress;
import com.jidu.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-16 12:40
 */
@CrossOrigin
@RestController
@RequestMapping(value="/app/address")
@Api(value = "收货地址", description = "收货地址")
public class AddressController extends  BaseController{
    @Autowired
    private AddressService addressService;
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "用户添加收货地址")
    public Result addShoppingAddress(@RequestBody ShoppingAddress shoppingAddress) {
        shoppingAddress.setUserId(userId);
        addressService.addShoppingAddress(shoppingAddress);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "用户编辑收货地址")
    public Result editShoppingAddress(@RequestBody ShoppingAddress shoppingAddress) {
        shoppingAddress.setUserId(userId);
        addressService.editShoppingAddress(shoppingAddress);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "我的收货地址")
    public Result<ShoppingAddress> findShoppingAddress() {
        List<ShoppingAddress> shoppingAddress= addressService.findShoppingAddress(userId);
        return new Result(ResultCode.SUCCESS,shoppingAddress);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除我的收货地址")
    public Result deleteShoppingAddress(@PathVariable long id) {
        addressService.deleteShoppingAddress(id);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "删除我的收货地址")
    public Result findShoppingAddressById(@PathVariable long id) {
        ShoppingAddress shoppingAddress=   addressService.findShoppingAddressById(id);
        return new Result(ResultCode.SUCCESS,shoppingAddress);
    }
    @RequestMapping(value = "/setDefault/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "设为默认")
    public Result setDefault(@PathVariable long id) {
        addressService.SetDefault(id,userId);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/cancelDefault/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "取消默认")
    public Result cancelDefault(@PathVariable long id) {
        addressService.cancelDefault(id);
        return new Result(ResultCode.SUCCESS);
    }
}
