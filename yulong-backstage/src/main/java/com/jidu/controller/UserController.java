package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.pojo.sys.AboutUs;
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
            @ApiImplicitParam(name="param",value="条件查询",required=false,paramType="query")
    })
    public PageResult search(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @RequestParam(required = false) Map param) {
        Page<UserInfo> page = PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> userInfos = userService.search(param);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
