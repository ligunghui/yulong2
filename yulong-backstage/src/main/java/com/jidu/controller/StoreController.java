package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.pojo.shop.ShoppingStore;
import com.jidu.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-26 16:00
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/store")
@Api(value = "商户操作", description = "商户操作")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "/{pageNum}/{pageSize}/{storeStatus}/{storeType}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path"),
            @ApiImplicitParam(name = "storeStatus", value = "状态(0全部1申请中2申请通过3申请不通过4关闭)", required = true, paramType = "path"),
            @ApiImplicitParam(name = "storeType", value = "类型(1普通商户 2本地服务商户)", required = true, paramType = "path")
    })
    public PageResult search(@PathVariable int pageNum, @PathVariable int pageSize, @RequestParam(required = false) Map param, @PathVariable int storeStatus, @PathVariable Integer storeType) {
        Page<ShoppingStore> page = PageHelper.startPage(pageNum, pageSize);
        List<ShoppingStore> shoppingStores = storeService.search(param, storeStatus,storeType);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path")
    })
    public Result findShoppingStoreById(@PathVariable String id) {
        ShoppingStore shoppingStore = storeService.findShoppingStoreById(id);
        return new Result(ResultCode.SUCCESS, shoppingStore);
    }

    @RequestMapping(value = "/{id}/{storeStatus}", method = RequestMethod.GET)
    @ApiOperation(value = "审核商户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path"),
            @ApiImplicitParam(name = "storeStatus", value = "2通过3拒绝", required = true, paramType = "path"),
            @ApiImplicitParam(name = "violationReseaon", value = "拒绝理由", required = false, paramType = "query")
    })
    public Result verify(@PathVariable String id, @PathVariable int storeStatus, @RequestParam(required = false) String violationReseaon) {

        return storeService.verify(id, violationReseaon, storeStatus);
    }
}
