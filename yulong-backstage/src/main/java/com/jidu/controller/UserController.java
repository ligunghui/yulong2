package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.sys.UserInfo;
import com.jidu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/3/6 0006 下午 5:15
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/user")
@Api(value = "会员管理", description = "会员管理")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询会员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path"),
            @ApiImplicitParam(name = "param", value = "条件查询", required = false, paramType = "query")
    })
    public PageResult search(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @RequestParam(required = false) Map param) {
        Page<UserInfo> page = PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> userInfos = userService.search(param);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改会员")
    public Result update(@RequestBody UserInfo userInfo) {
        userService.update(userInfo);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询会员")
    public Result findById(@PathVariable String id) {
        UserInfo userInfo = userService.findById(id);
        return new Result(ResultCode.SUCCESS, userInfo);
    }

    @RequestMapping(value = "/{pageNum}/{pageSize}/{authentication}", method = RequestMethod.GET)
    @ApiOperation(value = "查询实名认证相关会员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path"),
            @ApiImplicitParam(name = "authentication", value = "是否实名认证0否1申请中2申请通过3申请不通过", required = true, paramType = "path"),
            @ApiImplicitParam(name = "param", value = "条件查询", required = false, paramType = "query")
    })
    public PageResult searchAuthentication(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @RequestParam(required = false) Map param, @PathVariable Integer authentication) {
        Page<UserInfo> page = PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> userInfos = userService.searchAuthentication(param, authentication);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @RequestMapping(value = "/delAuthentication/{userId}/{authentication}", method = RequestMethod.GET)
    @ApiOperation(value = "处理实名认证相关会员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", required = true, paramType = "path"),
            @ApiImplicitParam(name = "authentication", value = "2申请通过3申请不通过", required = true, paramType = "path")
    })
    public Result delAuthentication(@PathVariable String userId, @PathVariable Integer authentication) {
        return   userService.delAuthentication(userId, authentication);
    }
    @RequestMapping(value = "/open/{userId}", method = RequestMethod.POST)
    @ApiOperation(value = "手动开通玉龙卡")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", required = true, paramType = "path")
    })
    public Result open(@PathVariable String userId) {
        return   userService.open(userId);
    }
}
