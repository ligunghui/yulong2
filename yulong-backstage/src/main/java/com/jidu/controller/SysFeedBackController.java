package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.aop.Log;
import com.jidu.entity.PageResult;
import com.jidu.pojo.sys.SysFeedback;
import com.jidu.service.LogService;
import com.jidu.service.SysFeedBackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/4/2 0002 上午 10:12
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/feedBack")
@Api(value = "问题反馈", description = "问题反馈")
public class SysFeedBackController {
    @Autowired
    private SysFeedBackService sysFeedBackService;
    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNum",value="当前页码",required=true,paramType="path"),
            @ApiImplicitParam(name="pageSize",value="每页条数",required=true,paramType="path")
    })
    public PageResult search (@PathVariable int pageNum, @PathVariable int pageSize) {
        Page<SysFeedback> page = PageHelper.startPage(pageNum, pageSize);
        List<SysFeedback> sysFeedbacks= sysFeedBackService.search();
        return new PageResult(page.getTotal(),page.getResult());
    }
}
