package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;

import com.jidu.pojo.sys.AboutUs;
import com.jidu.service.AboutUsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/6 0006 下午 2:56
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/aboutUs")
@Api(value = "关于我们", description = "关于我们")
public class AboutUsController {

    @Autowired
    private AboutUsService aboutUsService;


    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改关于我们")
    public Result update(@RequestBody AboutUs aboutUs) {
        aboutUsService.update(aboutUs);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询关于我们")
    public Result<AboutUs> findById(@PathVariable Integer id) {
        AboutUs aboutUs = aboutUsService.findById(id);
        return new Result(ResultCode.SUCCESS, aboutUs);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除关于我们")
    public Result delete(@PathVariable Integer id) {
        aboutUsService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询关于我们")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    public PageResult<AboutUs> search(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        Page<AboutUs> page = PageHelper.startPage(pageNum, pageSize);
        List<AboutUs> aboutUs = aboutUsService.search();
        return new PageResult(page.getTotal(), page.getResult());
    }
}