package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.local.LocalServiceStore;
import com.jidu.service.LocalServiceStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/13 0013 下午 8:09
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/LocalServiceStore")
@Api(value = "本地服务商家", description = "本地服务商家")
public class LocalServiceStoreController {
    @Autowired
    private LocalServiceStoreService localServiceStoreService;

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改本地服务商家")
    public Result update(@RequestBody LocalServiceStore localServiceStore) {
        localServiceStoreService.update(localServiceStore);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加本地服务")
    public Result add(@RequestBody LocalServiceStore localServiceStore) {
        localServiceStoreService.add(localServiceStore);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询本地服务")
    public Result<LocalServiceStore> findById(@PathVariable Integer id) {
        LocalServiceStore localServiceStore = localServiceStoreService.findById(id);
        return new Result(ResultCode.SUCCESS, localServiceStore);
    }


    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询本地服务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    public PageResult<LocalServiceStore> search(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        Page<LocalServiceStore> page = PageHelper.startPage(pageNum, pageSize);
        List<LocalServiceStore> localServiceStore = localServiceStoreService.search();
        return new PageResult(page.getTotal(), page.getResult());
    }
}
