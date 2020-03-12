package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.sys.AppAdvertisement;
import com.jidu.service.AppAdvertisementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/10  下午 6:18
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/appAdvertisement")
@Api(value = "app广告页", description = "app广告页")
public class AppAdvertisementController {
    @Autowired
    private AppAdvertisementService appAdvertisementService;
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加app广告页")
    public Result add(@RequestBody AppAdvertisement appAdvertisement) {
        appAdvertisementService.add(appAdvertisement);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除")
    public Result delete(@PathVariable Integer id) {
        appAdvertisementService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询app广告页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    public PageResult<AppAdvertisement> search(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        Page<AppAdvertisement> page = PageHelper.startPage(pageNum, pageSize);
        List<AppAdvertisement> appAdvertisements = appAdvertisementService.search();
        return new PageResult(page.getTotal(), page.getResult());
    }

}
