package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.notice.ShoppingNotice;
import com.jidu.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-10 16:40
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/notice")
@Api(value = "公告 系统消息", description = "公告 系统消息")

public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加公告 系统消息")
    @RequiresPermissions("notice_add")
    public Result save(@RequestBody ShoppingNotice shoppingNotice) {
        noticeService.save(shoppingNotice);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改公告 系统消息")
    @RequiresPermissions("notice_edit")
    public Result update(@RequestBody ShoppingNotice shoppingNotice) {
        noticeService.update(shoppingNotice);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询公告 系统消息")
    @RequiresPermissions("notice_find")
    public Result findById(@PathVariable Integer id) {
        ShoppingNotice shoppingNotice = noticeService.findById(id);
        return new Result(ResultCode.SUCCESS, shoppingNotice);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除公告 系统消息")
    @RequiresPermissions("notice_find")
    public Result delete(@PathVariable Integer id) {
        noticeService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/findByStoreId/{storeId}/{type}", method = RequestMethod.GET)
    @ApiOperation(value = "查询公告 系统消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeId", value = "商铺(户)id 平台传0", required = true, paramType = "query"),
            @ApiImplicitParam(name = "type", value = "类型 1系统消息 2平台公告 3商会公告 4商户公告", required = true, paramType = "query")
    })
    @RequiresPermissions("notice_find")
    public Result search(@PathVariable String storeId, @PathVariable Integer type) {
        List<ShoppingNotice> shoppingNotice = noticeService.search(storeId,type);
        return new Result(ResultCode.SUCCESS, shoppingNotice);
    }
}

