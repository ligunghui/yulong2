package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.shop.ChamberStore;
import com.jidu.pojo.shop.ShoppingStore;
import com.jidu.service.ChamberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-10 15:53
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/chamber/recommend")
@Api(value = "商户推荐", description = "商户推荐")
public class RecommendController extends BusinessBaseController {
    @Autowired
    private ChamberService chamberService;

    @RequestMapping(value = "/recommend/{sId}", method = RequestMethod.GET)
    @ApiOperation(value = "商会推荐商户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sId", value = "商户id", required = true, paramType = "path")
    })
    public Result recommend(@PathVariable String sId) {
        Integer chamberId = Integer.parseInt(storeId);
        return chamberService.recommend(chamberId, sId);
    }

    @RequestMapping(value = "/cancelRecommend/{sId}", method = RequestMethod.GET)
    @ApiOperation(value = "商会取消推荐商户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sId", value = "商户id", required = true, paramType = "path")
    })
    public Result cancelRecommend(@PathVariable String sId) {
        Integer chamberId = Integer.parseInt(storeId);
        return chamberService.cancelRecommend(chamberId, sId);
    }

    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商会推荐商户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    public PageResult<ShoppingStore> searchRecommend(@PathVariable int pageNum, @PathVariable int pageSize, @RequestParam(required = false) Map param) {
        Integer chamberId = Integer.parseInt(storeId);
        List<ShoppingStore> list = new ArrayList<>();
        Page<ShoppingStore> page = PageHelper.startPage(pageNum, pageSize);
        List<ChamberStore> chamberStores = chamberService.searchRecommend(param, chamberId);
        for (ChamberStore chamberStore : chamberStores) {
            String storeId = chamberStore.getStoreId();
            ShoppingStore shoppingStore = chamberService.findByShoppingStoreById(storeId);

            list.add(shoppingStore);
        }
        return new PageResult(page.getTotal(), list);
    }

    @RequestMapping(value = "/noRecommend", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有还未被推荐的商户")
    public Result noRecommend() {
        List<ShoppingStore> shoppingStores = chamberService.noRecommend();
        return new Result(ResultCode.SUCCESS, shoppingStores);
    }
}
