package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.aop.Log;
import com.jidu.entity.PageResult;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/3/24 0024 上午 11:16
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/log")
@Api(value = "日志", description = "日志")
public class LogController {
    @Autowired
    private LogService logService;
    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNum",value="当前页码",required=true,paramType="path"),
            @ApiImplicitParam(name="pageSize",value="每页条数",required=true,paramType="path")
    })
    public PageResult search (@PathVariable int pageNum, @PathVariable int pageSize) {
        Page<Log> page = PageHelper.startPage(pageNum, pageSize);
        List<Log> shoppingGoods= logService.search();
        return new PageResult(page.getTotal(),page.getResult());
    }

}
