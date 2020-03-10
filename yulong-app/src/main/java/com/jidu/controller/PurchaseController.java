package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.goods.GiftPack;
import com.jidu.pojo.goods.GoodsType;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.shop.ShoppingBanner;
import com.jidu.service.BannerService;
import com.jidu.service.PurchaseService;
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
 * @create: 2020-02-22 15:20
 */
@CrossOrigin
@RestController
@RequestMapping(value="/app/purchase")
@Api(value = "智汇购", description = "智汇购")
public class PurchaseController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private PurchaseService purchaseService;
    @RequestMapping(value = "/findGoodsType", method = RequestMethod.GET)
    @ApiOperation(value = "商品分类")
    public Result<GoodsType>  findGoodsType() {
        List<GoodsType> goodsTypes = purchaseService.findGoodsType();
        return new Result(ResultCode.SUCCESS, goodsTypes);
    }
    @RequestMapping(value = "/findBanner/{location}", method = RequestMethod.GET)
    @ApiOperation(value = "轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "location", value = "位置2智汇购3新人专区", required = true, paramType = "path")
    })
    public Result<ShoppingBanner> findBanner(@PathVariable Integer location) {
        List<ShoppingBanner> shoppingAddress = bannerService.findBanner("0",location);
        return new Result(ResultCode.SUCCESS, shoppingAddress);
    }
    @RequestMapping(value = "/findGoods/{pageNum}/{pageSize}/{isNew}", method = RequestMethod.GET)
    @ApiOperation(value = "智汇购商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path"),
            @ApiImplicitParam(name = "isNew", value = "新人专区(1是0否)", required = true, paramType = "path")
    })
    public Result<ShoppingGoods> findGoods(@PathVariable int pageNum, @PathVariable int pageSize, @PathVariable Integer isNew) {
        Page<ShoppingGoods> page = PageHelper.startPage(pageNum, pageSize);
        List<ShoppingGoods> shoppingGoods = purchaseService.findGoods(isNew);
        return new Result(ResultCode.SUCCESS, shoppingGoods);
    }

    @RequestMapping(value = "/findGiftPack/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "礼包专区")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    public Result<GiftPack> findGiftPack(@PathVariable int pageNum, @PathVariable int pageSize) {
        Page<GiftPack> page = PageHelper.startPage(pageNum, pageSize);
        List<GiftPack> giftPacks = purchaseService.findGiftPack();
        return new Result(ResultCode.SUCCESS, giftPacks);
    }
    @RequestMapping(value = "/findGiftPackById/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "礼包专区详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path"),
    })
    public Result<GiftPack> findGiftPackById(@PathVariable long id) {
      GiftPack giftPacks = purchaseService.findGiftPackById(id);
        return new Result(ResultCode.SUCCESS, giftPacks);
    }
    //平台自营 家乡特产 米粒兑换
}
