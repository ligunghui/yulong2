package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.Tree;
import com.jidu.service.ChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: liguanghui
 * Date: 2020/3/20 0020 下午 6:39
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/channel")
@Api(value = "频道", description = "频道")
@RequiresPermissions("channel_management")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询树形结构")
    public Result<Tree> findAll(@PathVariable Integer id) {
        return channelService.findAll(id);
    }
    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    @ApiOperation(value = "添加")
    public Result add(@PathVariable Integer id, @RequestParam(value = "typeIds[]") Integer[] typeIds) {
        channelService.add(id, typeIds);
        return new Result(ResultCode.SUCCESS);
    }
}
