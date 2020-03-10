package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.address.ShoppingAddress;
import com.jidu.pojo.notice.ShoppingNotice;
import com.jidu.pojo.sys.AboutUs;
import com.jidu.pojo.sys.AppUpdate;
import com.jidu.service.SysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/6 0006 下午 3:17
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/app/sys")
@Api(value = "平台信息", description = "平台信息")
public class SysController {
    @Autowired
    private SysService sysService;

    @RequestMapping(value = "/findAboutAs", method = RequestMethod.GET)
    @ApiOperation(value = "查询关于我们")
    public Result findAboutAs() {
       AboutUs aboutUs = sysService.findAboutAs();
        return new Result(ResultCode.SUCCESS, aboutUs);
    }
    @RequestMapping(value = "/findNotice", method = RequestMethod.GET)
    @ApiOperation(value = "系统通知消息接口")
    public Result findNotice() {
        List<ShoppingNotice> shoppingNotices = sysService.findNotice();
        return new Result(ResultCode.SUCCESS, shoppingNotices);
    }
    @RequestMapping(value = "/findAppUpdate", method = RequestMethod.GET)
    @ApiOperation(value = "app更新")
    public Result findAppUpdate() {
        List<AppUpdate> appUpdate = sysService.findAppUpdate();
        return new Result(ResultCode.SUCCESS, appUpdate);
    }
    @RequestMapping(value = "/findAppUpdateById/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "app更新详情")
    public Result findAppUpdateById(@PathVariable int id) {
        AppUpdate appUpdate = sysService.findAppUpdateById(id);
        return new Result(ResultCode.SUCCESS, appUpdate);
    }
}














