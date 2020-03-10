package com.jidu.chat.controller;

import com.alibaba.fastjson.JSON;
import com.jidu.chat.pojo.Room;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-21 16:19
 */

@RestController
@CrossOrigin
@RequestMapping(value = "/room")
@Api(value = "房间相关", tags = {"房间相关"})
public class RoomController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "createRoom", method = RequestMethod.POST)
    @ApiOperation(value = "创建房间")
    public Result createRoom(@RequestBody Room room) {
        Long size = this.redisTemplate.opsForHash().size("room");
        String id = (size.longValue() + 100L) + "";
        room.setId(id);
        String s = JSON.toJSONString(room);

        this.redisTemplate.opsForHash().put("room", id, s);
        String type = room.getType();
        this.redisTemplate.opsForList().rightPush(type, s);

        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "findRoomByType/{type}", method = RequestMethod.GET)
    @ApiOperation(value ="按照类型查找房间")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "房间类型", dataType = "string", paramType = "path")})
    public Result findRoomByType(@PathVariable String type) {
        List rooms = new ArrayList();
        List<String> list = this.redisTemplate.opsForList().range(type, 0L, -1L);
        for (String s : list) {
            Room room = JSON.parseObject(s, Room.class);
            rooms.add(room);
        }
        return new Result(ResultCode.SUCCESS, rooms);
    }
}
