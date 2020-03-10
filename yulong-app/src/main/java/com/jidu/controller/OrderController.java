package com.jidu.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.order.ShoppingOrder;
import com.jidu.service.OrderService;
import com.jidu.utils.KdApiOrderDistinguish;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-06 13:45
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/app/order")
@Api(value = "订单", description = "订单")
public class OrderController extends BaseController {
    // 电商ID
    private String EBusinessID = "1551827";
    // 电商加密私钥，快递鸟提供，注意保管，不要泄漏
    private String AppKey = "5297d7b3-d0ac-4690-aa92-05534cab4e91";
    // 请求url
    private String ReqURL = "http://api.kdniao.com/Ebusiness/EbusinessOrderHandle.aspx";
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询订单")
    public Result<ShoppingOrder> findById(@PathVariable String id) {
        ShoppingOrder shoppingOrder = orderService.findById(id);
        return new Result(ResultCode.SUCCESS, shoppingOrder);
    }
//取消订单，确认收货，订单评价，订单退换货

    @RequestMapping(value = "/operationOrder/{orderId}/{type}", method = RequestMethod.GET)
    @ApiOperation(value = "取消订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true, paramType = "path"),
            @ApiImplicitParam(name = "type", value = "操作类型(3确认收货4申请退货6取消订单)", required = true, paramType = "path")
    })
    public Result<ShoppingOrder> operationOrder(@PathVariable String orderId, @PathVariable Integer type) {
        orderService.operationOrder(orderId, type);
        return new Result(ResultCode.SUCCESS);
    }


    // 查询物流
    @RequestMapping(value = "/findLogisticsByOrderId/{orderId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询物流")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true, paramType = "path")
    })
    public Result logistics(@PathVariable String orderId) throws Exception {
        // 根据订单id查询单号
        Map<String, Object> map = new HashMap<String, Object>();

        ShoppingOrder shoppingOrder = orderService.findById(orderId);
        if (shoppingOrder == null || shoppingOrder.getShipcode() == null || shoppingOrder.getOrderStatus() < 2 || shoppingOrder.getOrderStatus() == 6) {
            return new Result(201, "未发货", false);
        }

        // 根据单号识别
        String expNo = shoppingOrder.getShipcode();// 物流单号
        KdApiOrderDistinguish api = new KdApiOrderDistinguish();
        String result1 = api.getOrderTracesByJson(expNo);

        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(result1);

        JSONArray shippers = jsonObject.getJSONArray("Shippers");
        net.sf.json.JSONObject object = (net.sf.json.JSONObject) shippers.get(0);
        String ShipperCode = object.getString("ShipperCode");

        String expCode = ShipperCode;// 快递公司编码

        String requestData = "{'OrderCode':'','ShipperCode':'" + expCode + "','LogisticCode':'" + expNo + "'}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("RequestData", KdApiOrderDistinguish.urlEncoder(requestData, "UTF-8"));
        params.put("EBusinessID", EBusinessID);
        params.put("RequestType", "1002");
        String dataSign = KdApiOrderDistinguish.encrypt(requestData, AppKey, "UTF-8");
        params.put("DataSign", KdApiOrderDistinguish.urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");

        String res = KdApiOrderDistinguish.sendPost(ReqURL, params);
        res = res.substring(res.indexOf("{"));// 截取 Map
        Map result = JSONObject.parseObject(res);// 转JSON
        Object object1 = result.get("Traces");
        map.put("Traces", object1);
        System.out.print(object1);
        return new Result(ResultCode.SUCCESS, map);

    }

}
