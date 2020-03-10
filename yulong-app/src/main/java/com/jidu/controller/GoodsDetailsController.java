package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.service.GoodsDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-23 10:47
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/app/goodsDetails")
@Api(value = "商品详情统一接口", description = "商品详情统一接口")
public class GoodsDetailsController {
    @Autowired
    private GoodsDetailsService goodsDetailsService;
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据id查询商品")
    public Result<ShoppingGoods> findById(@PathVariable long id) {
        ShoppingGoods shoppingGoods = goodsDetailsService.findById(id);
        return new Result(ResultCode.SUCCESS, shoppingGoods);
    }
}
