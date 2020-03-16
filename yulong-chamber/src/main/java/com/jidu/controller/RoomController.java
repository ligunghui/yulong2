package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.notice.ShoppingNotice;
import com.jidu.pojo.room.RoomChat;
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
 * Date: 2020/3/16 0016 上午 9:49
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/chamber/room")
@Api(value = "房间", description = "房间")
public class RoomController extends BusinessBaseController {
    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "创建房间")
    public Result save(@RequestBody RoomChat roomChat) {
        roomChat.setChamberId(Integer.parseInt(storeId));
        roomChat.setStatus(1);
        roomService.save(roomChat);

        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除房间")
    public Result delete(@PathVariable Integer id) {
        roomService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "修改房间")
    public Result update(@RequestBody RoomChat roomChat) {
        roomService.update(roomChat);

        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询")
    public Result findByStoreId() {
        List<RoomChat> roomChats = roomService.findByStoreId(storeId);
        return new Result(ResultCode.SUCCESS,roomChats);
    }
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询")
    public Result findById(@PathVariable Integer id) {
        RoomChat roomChat = roomService.findById(id);
        return new Result(ResultCode.SUCCESS,roomChat);
    }
}
