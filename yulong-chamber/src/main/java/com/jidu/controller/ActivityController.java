package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.activity.ShoppingActivity;
import com.jidu.pojo.shop.ShoppingChamber;
import com.jidu.pojo.sys.UserInfo;
import com.jidu.service.ActivityService;
import com.jidu.service.ChamberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(value = "/chamber/activity")
@Api(value = "1商会动态4本地新闻", description = "1商会动态2政府资3文化旅游4本地新闻")
public class ActivityController extends BusinessBaseController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ChamberService chamberService;
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "type 1商会动态2政府资3文化旅游4本地新闻")
    public Result save(@RequestBody ShoppingActivity shoppingActivity) {
        ShoppingChamber shoppingChamber = chamberService.findById(Integer.parseInt(storeId));
        if (shoppingChamber == null) {
            return new Result(201, "商会不存在", false);
        }
        shoppingActivity.setStoreId(storeId);
        shoppingActivity.setStoreName(shoppingChamber.getName());
        activityService.save(shoppingActivity);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改商会动态2政府资讯3文化旅游4本地新闻")
    public Result update(@RequestBody ShoppingActivity shoppingActivity) {
        activityService.update(shoppingActivity);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商会动态")
    public Result<ShoppingActivity> findById(@PathVariable int id) {
        ShoppingActivity shoppingActivity = activityService.findById(id);
        return new Result(ResultCode.SUCCESS, shoppingActivity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除商会动态")
    public Result delete(@PathVariable int id) {
        activityService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/findByStoreId", method = RequestMethod.GET)
    @ApiOperation(value = "查询商会动态")
    public Result<ShoppingActivity> search() {
        List<ShoppingActivity> shoppingActivity = activityService.search(storeId);
        return new Result(ResultCode.SUCCESS, shoppingActivity);
    }

    @RequestMapping(value = "/findActivityUser/{activityId}", method = RequestMethod.GET)
    @ApiOperation(value = "活动报名的人员")
    public Result<UserInfo> findActivityUser(@PathVariable String activityId) {
        List<UserInfo> shoppingActivity = activityService.findActivityUser(activityId);
        return new Result(ResultCode.SUCCESS, shoppingActivity);
    }
}


