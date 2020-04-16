package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.local.LocalService;
import com.jidu.pojo.local.LocalServiceGoods;
import com.jidu.pojo.shop.ShoppingStore;
import com.jidu.service.GoodsService;
import com.jidu.service.LocalServiceGoodsService;
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
 * @Author: liguanghui
 * Date: 2020/3/25 0025 下午 1:58
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/business/localServiceGoods")
@Api(value = "本地服务商品操作", description = "本地服务商品操作")
public class LocalServiceGoodsController extends BusinessBaseController {
    @Autowired
    private LocalServiceGoodsService localServiceGoodsService;
    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加商品")
    public Result save(@RequestBody LocalServiceGoods localServiceGoods) {
        ShoppingStore shoppingStore = storeService.findById(storeId);
        if (localServiceGoods == null) {
            return new Result(201, "商户不存在", false);
        }
        localServiceGoods.setStoreId(storeId);
        localServiceGoodsService.save(localServiceGoods);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改商品")
    public Result update(@RequestBody LocalServiceGoods localServiceGoods) {
        localServiceGoodsService.update(localServiceGoods);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商品")
    public Result findById(@PathVariable Long id) {
        LocalServiceGoods localServiceGoods = localServiceGoodsService.findById(id);
        return new Result(ResultCode.SUCCESS, localServiceGoods);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询所勾选的类型")
    public Result findService() {
        List<LocalService> localService = localServiceGoodsService.findService(storeId);
        return new Result(ResultCode.SUCCESS, localService);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除商品")
    public Result delete(@PathVariable Long id) {
        localServiceGoodsService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    public PageResult<LocalServiceGoods> search(@PathVariable int pageNum, @PathVariable int pageSize, @RequestParam(required = false) Map param) {
        Page<LocalServiceGoods> page = PageHelper.startPage(pageNum, pageSize);
        List<LocalServiceGoods> localServiceGoods = localServiceGoodsService.search(param, storeId);
        return new PageResult(page.getTotal(), page.getResult());
    }
}