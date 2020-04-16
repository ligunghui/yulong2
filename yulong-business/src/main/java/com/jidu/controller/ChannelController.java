package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.RequestManualAddData;
import com.jidu.pojo.RequestTree;
import com.jidu.pojo.Tree;
import com.jidu.pojo.grade.SysGrade;
import com.jidu.service.ChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/3/23 0023 下午 6:23
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/business/channel")
@Api(value = "频道", description = "频道")
public class ChannelController extends  BusinessBaseController{
    @Autowired
    private ChannelService channelService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询树形结构")
    public Result<Tree> findAll() {
        return channelService.findAll(storeId);
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加")
    public Result add( @RequestBody List<RequestTree> list) {
        Map<Integer,List<Integer>> map=new HashMap<>();
        for (RequestTree requestTree : list) {
           map.put(requestTree.getId(),requestTree.getChildren());
        }
        channelService.add(storeId,map);
        return new Result(ResultCode.SUCCESS);
    }
}
