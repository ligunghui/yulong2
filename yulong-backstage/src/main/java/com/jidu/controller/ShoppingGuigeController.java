package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.shop.ShoppingGuige;
import com.jidu.service.ShoppingGuigeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/4/14 0014 上午 9:09
 * @Version:
 * @Description:
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/guige")
@Api(value = "规格", description = "规格")
public class ShoppingGuigeController {
    @Autowired
    private ShoppingGuigeService shoppingGuigeService;
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加规格")
    public Result save(@RequestBody List<ShoppingGuige> shoppingGuiges) {
        shoppingGuigeService.save(shoppingGuiges);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改规格")
    public Result update(@RequestBody ShoppingGuige shoppingGuige) {
        shoppingGuigeService.update(shoppingGuige);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询规格")
    public Result<ShoppingGuige> findById(@PathVariable Integer id) {
        ShoppingGuige shoppingGuige = shoppingGuigeService.findById(id);
        return new Result(ResultCode.SUCCESS, shoppingGuige);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除规格")
    public Result delete(@PathVariable Integer id) {
        shoppingGuigeService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "findByGoodsId/{goodsId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询规格")
    public Result<ShoppingGuige> search(@PathVariable String goodsId) {
        List<ShoppingGuige> shoppingGuige = shoppingGuigeService.search(goodsId);
        return new Result(ResultCode.SUCCESS, shoppingGuige);
    }
}
