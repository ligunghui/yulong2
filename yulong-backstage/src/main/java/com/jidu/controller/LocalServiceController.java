package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.local.LocalService;
import com.jidu.pojo.local.LocalServiceStore;
import com.jidu.pojo.sys.AboutUs;
import com.jidu.service.LocalServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/13 0013 下午 7:36
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/localService")
@Api(value = "本地服务", description = "本地服务")
public class LocalServiceController {
    @Autowired
    private LocalServiceService localServiceService;

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改本地服务")
    @RequiresPermissions("service_type_edit")
    public Result update(@RequestBody LocalService localService) {
        localServiceService.update(localService);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加本地服务")
    @RequiresPermissions("service_type_add")
    public Result add(@RequestBody LocalService localService) {
        localServiceService.add(localService);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询本地服务")
    @RequiresPermissions("service_type_find")
    public Result<LocalService> findById(@PathVariable Integer id) {
        LocalService localService = localServiceService.findById(id);
        return new Result(ResultCode.SUCCESS, localService);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除")
    @RequiresPermissions("service_type_delete")
    public Result DELETE(@PathVariable Integer id) {
        localServiceService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/findByServiceId/{serviceId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询本地服务的店家")
    @RequiresPermissions("service_business_find")
    public Result<LocalServiceStore> findByServiceId(@PathVariable Integer serviceId) {
        List<LocalServiceStore> localServiceStores = localServiceService.findByServiceId(serviceId);
        return new Result(ResultCode.SUCCESS, localServiceStores);
    }


    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询本地服务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    @RequiresPermissions("service_type_find")
    public PageResult<LocalService> search(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        Page<LocalService> page = PageHelper.startPage(pageNum, pageSize);
        List<LocalService> localService = localServiceService.search();
        return new PageResult(page.getTotal(), page.getResult());
    }
}