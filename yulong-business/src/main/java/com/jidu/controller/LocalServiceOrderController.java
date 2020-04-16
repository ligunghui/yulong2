package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.local.LocalServiceOrder;
import com.jidu.service.LocalServiceOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/25 0025 下午 7:06
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/business/localService")
@Api(value = "本地服务订单操作", description = "本地服务订单操作")
public class LocalServiceOrderController extends  BusinessBaseController {
    @Autowired
    private LocalServiceOrderService localServiceOrderService;
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改订单")
    public Result update(@RequestBody LocalServiceOrder localServiceOrder) {
        localServiceOrderService.update(localServiceOrder);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询订单")
    public Result<LocalServiceOrder> findById(@PathVariable Integer id) {
        LocalServiceOrder localServiceOrder = localServiceOrderService.findById(id);
        return new Result(ResultCode.SUCCESS, localServiceOrder);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除订单")
    public Result delete(@PathVariable Integer id) {
        localServiceOrderService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/findByStoreId/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    public PageResult<LocalServiceOrder> search(@PathVariable int pageNum, @PathVariable int pageSize) {
        Page<LocalServiceOrder> page = PageHelper.startPage(pageNum, pageSize);
        List<LocalServiceOrder> localServiceOrder = localServiceOrderService.search(storeId);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
