package com.jidu.controller;

import com.alibaba.fastjson.JSON;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.order.OrderItem;
import com.jidu.pojo.shop.ShoppingCart;
import com.jidu.service.CartService;
import com.jidu.utils.CookieUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-13 13:09
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/app/cart")
@Api(value = "购物车", description = "购物车")
public class CartController extends BaseController {
    @Autowired
    private CartService cartService;

    @ApiOperation(value = "查找购物车")

    @RequestMapping(value = "findCartList", method = RequestMethod.GET)
    public List<ShoppingCart> findCartList() {

        String cartListString = CookieUtil.getCookieValue(request, "cartList", "UTF-8");
        if (cartListString == null || cartListString.equals("")) {
            cartListString = "[]";
        }
        List<ShoppingCart> cartList_cookie = JSON.parseArray(cartListString, ShoppingCart.class);

        if (StringUtils.isEmpty(userId)) {//如果未登录
            System.out.println("从cookie中提取购物车");
            return cartList_cookie;
        } else {//如果已登录
            //获取redis购物车
            List<ShoppingCart> cartList_redis = cartService.findCartListFromRedis(userId);
            if (cartList_cookie.size() > 0) {//判断当本地购物车中存在数据
                //得到合并后的购物车
                List<ShoppingCart> cartList = cartService.mergeCartList(cartList_cookie, cartList_redis);
                //将合并后的购物车存入redis
                cartService.saveCartListToRedis(userId, cartList);
                //本地购物车清除
                CookieUtil.deleteCookie(request, response, "cartList");
                System.out.println("执行了合并购物车的逻辑");
                return cartList;
            }
            return cartList_redis;
        }
    }

    @ApiOperation(value = "添加(减少)商品到购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", value = "商品id", required = true, paramType = "path"),
            @ApiImplicitParam(name = "num", value = "数量 减少传负值", required = true, paramType = "path"),
    })
    @RequestMapping(value = "/addGoodsToCartList/{goodsId}/{num}", method = RequestMethod.GET)
    public Result addGoodsToCartList(@PathVariable Long goodsId, @PathVariable Integer num) {
        List<ShoppingCart> cartList = findCartList();
        cartList = cartService.addGoodsToCartList(cartList, goodsId, num);
        if (StringUtils.isEmpty(userId)) {
            System.out.println("用户未登录");
            String cartListString = JSON.toJSONString(cartList);
            CookieUtil.setCookie(request, response, "cartList", cartListString, 3600 * 24, "UTF-8");
        } else {
            //存储到redis中
            cartService.saveCartListToRedis(userId, cartList);
        }
        return new Result(ResultCode.SUCCESS);
    }

}
