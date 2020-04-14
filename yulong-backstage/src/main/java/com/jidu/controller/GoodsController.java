package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.aop.LogAnno;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.service.GoodsService;
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
 * @create: 2020-02-05 14:48
 */
@CrossOrigin
@RestController
@RequestMapping(value="/backstage/goods")
@Api(value = "商品操作", description = "商品操作")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加商品")
    @LogAnno(operateType = "添加商品")
    public Result save(@RequestBody ShoppingGoods shoppingGoods) {
        goodsService.save(shoppingGoods);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改商品")
    @LogAnno(operateType = "修改商品")
    public Result update(@RequestBody ShoppingGoods shoppingGoods) {
        goodsService.update(shoppingGoods);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商品")
    public Result findById(@PathVariable long id) {
       ShoppingGoods shoppingGoods= goodsService.findById(id);
        return new Result(ResultCode.SUCCESS,shoppingGoods);
    }
    @RequestMapping(value = "/findByGoodsId/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商品")
    public ShoppingGoods findByGoodsId(@PathVariable long id) {
       ShoppingGoods shoppingGoods= goodsService.findById(id);
        return  shoppingGoods;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除商品")
    @LogAnno(operateType = "删除商品")
    public Result delete(@PathVariable long id) {
        goodsService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNum",value="当前页码",required=true,paramType="path"),
            @ApiImplicitParam(name="pageSize",value="每页条数",required=true,paramType="path")
    })
    public PageResult search (@PathVariable int pageNum, @PathVariable int pageSize, @RequestParam(required = false) Map param) {
            Page<ShoppingGoods> page = PageHelper.startPage(pageNum, pageSize);
            List<ShoppingGoods> shoppingGoods= goodsService.search(param);
        return new PageResult(page.getTotal(),page.getResult());
    }

}
