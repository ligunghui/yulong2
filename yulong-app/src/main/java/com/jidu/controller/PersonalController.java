package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.order.ShoppingOrder;
import com.jidu.pojo.shop.ChamberUser;
import com.jidu.pojo.shop.ShoppingChamber;
import com.jidu.pojo.shop.ShoppingStore;
import com.jidu.service.PersonalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-11 15:14
 */
@CrossOrigin
@RestController
@RequestMapping(value="/app/personal")
@Api(value = "个人中心", description = "个人中心")
public class PersonalController extends  BaseController{
    @Autowired
    private PersonalService personalService;

    @RequestMapping(value = "/findCollectByUserId", method = RequestMethod.GET)
    @ApiOperation(value = "商品收藏")

    public Result<ShoppingGoods> findCollectByUserId () {

        List<ShoppingGoods> shoppingGoods= personalService.findCollectByUserId(userId);
        return new Result(ResultCode.SUCCESS,shoppingGoods);
    }
    @RequestMapping(value = "/findOrderByUserId/{status}", method = RequestMethod.GET)
    @ApiOperation(value = "我的订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name="status",value="状态(0待支付1待发货2待收货3待评价4申请退货5已退货)",required=true,paramType="path")
    })
    public Result<ShoppingOrder> findOrderByUserId ( @PathVariable int status) {

        List<ShoppingOrder> orders= personalService.findOrderByUserId(userId,status);
        return new Result(ResultCode.SUCCESS,orders);
    }
    @RequestMapping(value = "UserApplyChamber", method = RequestMethod.POST)
    @ApiOperation(value = "用户申请加入商会")
    public Result UserApplyChamber(@RequestBody ChamberUser chamberUser) {
        chamberUser.setUserId(userId);
        return  personalService.UserApplyChamber(chamberUser);
    }
    @RequestMapping(value = "/cancelChamberByUserId", method = RequestMethod.GET)
    @ApiOperation(value = "用户退出商会")
    public Result cancelChamberByUserId() {
        return  personalService.cancelChamberByUserId(userId);
    }
    @RequestMapping(value = "/findChamberByUserId", method = RequestMethod.GET)
    @ApiOperation(value = "我的商会")
    public Result<ShoppingChamber> findChamberByUserId() {
        return  personalService.findChamberByUserId(userId);
    }
    @RequestMapping(value = "UserApplyBusiness", method = RequestMethod.POST)
    @ApiOperation(value = "用户申请成为商户")
    public Result UserApplyBusiness(@RequestBody ShoppingStore shoppingStore) {

        shoppingStore.setOwerId(userId);
        return  personalService.UserApplyBusiness(shoppingStore);
    }
}
