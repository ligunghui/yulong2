package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.room.RoomChat;
import com.jidu.pojo.sys.IncomeSetting;
import com.jidu.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/16 0016 上午 11:13
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/room")
@Api(value = "聊天室", description = "聊天室")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除房间")
    public Result delete(@PathVariable Integer id) {
        roomService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/{id}/{storeStatus}", method = RequestMethod.GET)
    @ApiOperation(value = "审核商户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path"),
            @ApiImplicitParam(name = "storeStatus", value = "2通过3拒绝", required = true, paramType = "path"),
            @ApiImplicitParam(name = "violationReseaon", value = "拒绝理由", required = false, paramType = "query")
    })
    public Result verify(@PathVariable Integer id, @PathVariable int storeStatus, @RequestParam(required = false) String violationReseaon) {

        return roomService.verify(id, violationReseaon, storeStatus);
    }
    @RequestMapping(value = "/{storeStatus}/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "按照状态查询商户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeStatus", value = "房间状态(全部0 1申请中2申请通过3申请不通过)", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    public PageResult verify(@PathVariable int storeStatus, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {

        Page<RoomChat> page = PageHelper.startPage(pageNum, pageSize);
        List<RoomChat> incomeSettings = roomService.search(storeStatus);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
