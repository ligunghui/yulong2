package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.order.StatisticsGroup;
import com.jidu.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * @Author: liguanghui
 * Date: 2020/3/10 0010 下午 3:25
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/business/home")
@Api(value = "首页", description = "首页")
public class HomeController extends  BusinessBaseController {
    @Autowired
    private HomeService homeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "首页")
    public Result index() throws ParseException {
        StatisticsGroup statisticsGroup = homeService.index(storeId);
        statisticsGroup.setUserName(userName);
        return new Result(ResultCode.SUCCESS, statisticsGroup);
    }
}
