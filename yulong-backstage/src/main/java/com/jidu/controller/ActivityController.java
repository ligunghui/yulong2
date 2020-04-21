package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.activity.ActivityUser;
import com.jidu.pojo.activity.ShoppingActivity;
import com.jidu.pojo.sys.UserInfo;
import com.jidu.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: yuint
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-18 15:48
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/activity")
@Api(value = "1商会动态2政府资3文化旅游4本地新闻", description = "1商会动态2政府资讯3文化旅游4本地新闻")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @RequiresPermissions(value = {"chamber_dynamics_add", "news_add",  "tourism_add", "investment_add"}, logical = Logical.OR)
    @ApiOperation(value = "type 1商会动态2政府资讯3文化旅游4本地新闻")
    public Result save(@RequestBody ShoppingActivity shoppingActivity) {
        activityService.save(shoppingActivity);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改商会动态2政府资讯3文化旅游4本地新闻")
    @RequiresPermissions(value = {"chamber_dynamics_edit", "news_edit",  "tourism_edit", "investment_edit"}, logical = Logical.OR)
    public Result update(@RequestBody ShoppingActivity shoppingActivity) {
        activityService.update(shoppingActivity);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商会动态")
    @RequiresPermissions(value = {"chamber_dynamics_find", "news_find",  "tourism_find", "investment_find"}, logical = Logical.OR)
    public Result<ShoppingActivity> findById(@PathVariable int id) {
        ShoppingActivity shoppingActivity = activityService.findById(id);
        return new Result(ResultCode.SUCCESS, shoppingActivity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除商会动态")
    @RequiresPermissions(value = {"chamber_dynamics_delete", "news_delete",  "tourism_delete", "investment_delete"}, logical = Logical.OR)
    public Result delete(@PathVariable int id) {
        activityService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/findByStoreId", method = RequestMethod.GET)
    @ApiOperation(value = "查询商会动态")
    @RequiresPermissions(value = {"chamber_dynamics_find", "news_find",  "tourism_find", "investment_find"}, logical = Logical.OR)
    public Result<ShoppingActivity> search() {
        List<ShoppingActivity> shoppingActivity = activityService.search();
        return new Result(ResultCode.SUCCESS, shoppingActivity);
    }


}


