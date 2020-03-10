package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.activity.ShoppingActivity;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.notice.ShoppingNotice;
import com.jidu.pojo.opportunities.BusinessOpportunities;
import com.jidu.pojo.shop.ShoppingBanner;
import com.jidu.service.BannerService;
import com.jidu.service.IndexService;
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
 * @create: 2020-02-17 15:05
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/app/index")
@Api(value = "首页", description = "首页")
public class IndexController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "/findBanner", method = RequestMethod.GET)
    @ApiOperation(value = "轮播图")
    public Result<ShoppingBanner> findBanner() {
        List<ShoppingBanner> shoppingAddress = bannerService.findBanner("0",1);
        return new Result(ResultCode.SUCCESS, shoppingAddress);
    }

    @RequestMapping(value = "/findNotice", method = RequestMethod.GET)
    @ApiOperation(value = "消息")
    public Result<ShoppingNotice> findNotice() {
        List<ShoppingNotice> shoppingNotices = noticeService.findNoticeByStoreId("0");
        return new Result(ResultCode.SUCCESS, shoppingNotices);
    }

    @RequestMapping(value = "/findActivityByType/{type}/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "多彩玉龙/商会新闻")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "1多彩玉龙2新闻", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    public List<ShoppingActivity> findActivityByType(@PathVariable int pageNum, @PathVariable int pageSize, @PathVariable String type) {
        Page<ShoppingActivity> page = PageHelper.startPage(pageNum, pageSize);
        List<ShoppingActivity> shoppingActivity = indexService.findActivityByType(type);
        return shoppingActivity;
    }

    @RequestMapping(value = "/findGoods", method = RequestMethod.GET)
    @ApiOperation(value = "智汇购")
    public List<ShoppingGoods> findGoods() {
        List<ShoppingGoods> shoppingGoods = indexService.findGoods();
        return shoppingGoods;
    }

    @RequestMapping(value = "/findOpportunities", method = RequestMethod.GET)
    @ApiOperation(value = "汇商机")
    public List<BusinessOpportunities> findOpportunities() {
        List<BusinessOpportunities> businessOpportunities = indexService.findOpportunities();
        return businessOpportunities;
    }
}
