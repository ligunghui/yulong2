package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.goods.GoodsType;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.notice.ShoppingNotice;
import com.jidu.pojo.shop.ShoppingBanner;
import com.jidu.service.BannerService;
import com.jidu.service.GoodsService;
import com.jidu.service.NoticeService;
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
 * @create: 2020-02-20 17:29
 */
@CrossOrigin
@RestController
@RequestMapping(value="/app/business")
@Api(value = "商户", description = "商户")
public class BusinessController {

    @Autowired
    private BannerService bannerService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/findBanner/{storeId}", method = RequestMethod.GET)
    @ApiOperation(value = "轮播图")
    public Result<ShoppingBanner> findBanner(@PathVariable String storeId) {
        List<ShoppingBanner> shoppingAddress = bannerService.findBanner(storeId,null);
        return new Result(ResultCode.SUCCESS, shoppingAddress);
    }

    @RequestMapping(value = "/findNoticeByStoreId/{storeId}", method = RequestMethod.GET)
    @ApiOperation(value = "商户消息")
    public Result<ShoppingNotice> findNotice(@PathVariable String storeId) {
        List<ShoppingNotice> shoppingNotices = noticeService.findNoticeByStoreId(storeId);
        return new Result(ResultCode.SUCCESS, shoppingNotices);
    }
    @RequestMapping(value = "/findGoodsByStoreIdAndType/{storeId}/{typeId}", method = RequestMethod.GET)
    @ApiOperation(value = "商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "商品类型id 全部传0", required = true, paramType = "path"),
            @ApiImplicitParam(name = "storeId", value = "商户id", required = true, paramType = "path"),
    })
    public Result<ShoppingGoods> findGoodsByStoreIdAndType(@PathVariable String storeId, @PathVariable Integer typeId) {
        List<ShoppingGoods>shoppingGoods = goodsService.findGoodsByStoreIdAndType(storeId,typeId);
        return new Result(ResultCode.SUCCESS, shoppingGoods);
    }
    @RequestMapping(value = "/findGoodsTypeByStoreId/{storeId}", method = RequestMethod.GET)
    @ApiOperation(value = "商品分类")
    public Result<GoodsType> findGoodsTypeByStoreId(@PathVariable String storeId) {
        List<GoodsType>goodsTypes = goodsService.findGoodsTypeByStoreId(storeId);
        return new Result(ResultCode.SUCCESS, goodsTypes);
    }

}