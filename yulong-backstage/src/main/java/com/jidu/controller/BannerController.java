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

@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/banner")
@Api(value = "轮播图", description = "轮播图")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加轮播图")
    public Result save(@RequestBody ShoppingBanner shoppingBanner) {
        bannerService.save(shoppingBanner);
        return new Result(ResultCode.SUCCESS);
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
        List<ShoppingBanner> shoppingBanner = bannerService.search();
        return new Result(ResultCode.SUCCESS, shoppingBanner);
    }

}
