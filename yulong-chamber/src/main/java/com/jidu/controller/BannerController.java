package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.shop.ShoppingBanner;
import com.jidu.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-06 11:14
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/chamber/banner")
@Api(value = "轮播图", description = "轮播图")
public class BannerController extends BusinessBaseController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加轮播图")
    public Result save(@RequestBody ShoppingBanner shoppingBanner) {
        shoppingBanner.setStoreId(storeId);
        bannerService.save(shoppingBanner);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改轮播图")
    public Result update(@RequestBody ShoppingBanner shoppingBanner) {
        bannerService.update(shoppingBanner);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询轮播图")
    public Result<ShoppingBanner> findById(@PathVariable long id) {
        ShoppingBanner shoppingBanner = bannerService.findById(id);
        return new Result(ResultCode.SUCCESS, shoppingBanner);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除轮播图")
    public Result delete(@PathVariable long id) {
        bannerService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/findByStoreId", method = RequestMethod.GET)
    @ApiOperation(value = "查询轮播图")
    public Result<ShoppingBanner> search() {
        List<ShoppingBanner> shoppingBanner = bannerService.search(storeId);
        return new Result(ResultCode.SUCCESS, shoppingBanner);
    }

}
