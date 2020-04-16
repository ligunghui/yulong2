package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.StoreGongGroup;
import com.jidu.pojo.shop.ShoppingStore;
import com.jidu.service.StoreRecommendGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/4/4 0004 上午 10:08
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/storeRecommendGoods")
@Api(value = "商户推荐的商品操作", description = "商户推荐的商品操作")
public class StoreRecommendGoodsController {
    @Autowired
    private StoreRecommendGoodsService storeRecommendGoodsService;

    @RequestMapping(value = "/{pageNum}/{pageSize}/{status}/{type}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商户推荐的商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path"),
            @ApiImplicitParam(name = "status", value = "1申请中2申请通过3驳回", required = true, paramType = "path"),
            @ApiImplicitParam(name = "type", value = "类型(1普通商品2本地服务类型商品)", required = true, paramType = "path")
    })
    public PageResult search(@PathVariable int pageNum, @PathVariable int pageSize, @RequestParam(required = false) Map param, @PathVariable int status, @PathVariable Integer type) {
        Page<StoreGongGroup> page = PageHelper.startPage(pageNum, pageSize);
        List<StoreGongGroup> storeGongGroups = storeRecommendGoodsService.search(param, status, type);
        return new PageResult(page.getTotal(),storeGongGroups);
    }

    @RequestMapping(value = "/{id}/{status}", method = RequestMethod.GET)
    @ApiOperation(value = "审核商户推荐的商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path"),
            @ApiImplicitParam(name = "status", value = "2申请通过3驳回", required = true, paramType = "path"),
    })
    public Result verify(@PathVariable Integer id, @PathVariable Integer status) {

        return storeRecommendGoodsService.verify(id, status);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "撤回推荐")
    public Result withdraw(@PathVariable Integer id) {
        storeRecommendGoodsService.withdraw(id);
        return new Result(ResultCode.SUCCESS);
    }
}
