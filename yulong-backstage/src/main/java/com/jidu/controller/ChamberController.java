package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.shop.ShoppingChamber;
import com.jidu.service.ChamberService;
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
 * @create: 2020-02-22 08:58
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/chamber")
@Api(value = "商会管理", description = "商会管理")
public class ChamberController {
    @Autowired
    private ChamberService chamberService;
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加商会")
    public Result save(@RequestBody ShoppingChamber shoppingChamber) {
        chamberService.save(shoppingChamber);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改商会")
    public Result update(@RequestBody ShoppingChamber shoppingChamber) {
        chamberService.update(shoppingChamber);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商会")
    public Result<ShoppingChamber> findById(@PathVariable Integer id) {
        ShoppingChamber shoppingChamber= chamberService.findById(id);
        return new Result(ResultCode.SUCCESS,shoppingChamber);
    }
    @RequestMapping(value = "/checkTelephone{telephone}", method = RequestMethod.GET)
    @ApiOperation(value = "检查是否注册")
    public Result checkTelephone(@PathVariable String telephone) {
        boolean b= chamberService.checkTelephone(telephone);
        if (b){
            return  new Result(200,"用户名可用",true);
        }
        return  new Result(201,"用户名重复",false);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除商会")
    public Result delete(@PathVariable Integer id) {
        chamberService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/{pageNum}/{pageSize}/{status}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商会")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNum",value="当前页码",required=true,paramType="path"),
            @ApiImplicitParam(name="pageSize",value="每页条数",required=true,paramType="path"),
            @ApiImplicitParam(name = "status", value = "状态(0全部1申请中2申请通过3申请不通过4关闭)", required = true, paramType = "path")

    })
    public PageResult<ShoppingChamber> search(@PathVariable int pageNum, @PathVariable int pageSize, @RequestParam(required = false) Map param, @PathVariable Integer status) {
        Page<ShoppingChamber> page = PageHelper.startPage(pageNum, pageSize);
        List<ShoppingChamber> shoppingChamber= chamberService.search(param,status);
        return new PageResult(page.getTotal(),page.getResult());
    }

}
