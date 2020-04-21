package com.jidu.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.order.OrderGoods;
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
@RequestMapping(value = "/business/order")
@Api(value = "订单", description = "订单")
public class OrderController extends BusinessBaseController {
    // 电商ID
    private String EBusinessID = "1551827";
    // 电商加密私钥，快递鸟提供，注意保管，不要泄漏
    private String AppKey = "5297d7b3-d0ac-4690-aa92-05534cab4e91";
    // 请求url
    private String ReqURL = "http://api.kdniao.com/Ebusiness/EbusinessOrderHandle.aspx";
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改订单")
    public Result update(@RequestBody ShoppingOrder shoppingOrder) {
        orderService.update(shoppingOrder);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询订单")
    public Result<ShoppingOrder> findById(@PathVariable String id) {
        ShoppingOrder shoppingOrder = orderService.findById(id);
        return new Result(ResultCode.SUCCESS, shoppingOrder);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除订单")
    public Result delete(@PathVariable String id) {
        orderService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/findByStoreId/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    public PageResult<ShoppingOrder> search(@PathVariable int pageNum, @PathVariable int pageSize) {
        Page<ShoppingOrder> page = PageHelper.startPage(pageNum, pageSize);
        List<ShoppingOrder> shoppingOrder = orderService.search(storeId);
        return new PageResult(page.getTotal(), page.getResult());
    }

    // 查询物流
    @RequestMapping(value = "/findOrderGoodsByOrderId/{orderId}", method = RequestMethod.GET)
    @ApiOperation(value = "通过订单查询商品")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true, paramType = "path")
    })
    public Result findOrderGoodsByOrderId(@PathVariable String orderId) throws Exception {
        List<OrderGoods> orderGoods = orderService.findOrderGoodsByOrderId(orderId);
        return new Result(ResultCode.SUCCESS, orderGoods);
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
        if (shoppingOrder == null || shoppingOrder.getShipcode() == null || shoppingOrder.getOrderStatus() < 2) {
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
    @RequestMapping(value = "/returnGoods/{orderId}/{state}", method = RequestMethod.GET)
    @ApiOperation(value = "是否允许退货")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = " 订单id", required = true, paramType = "path"),
            @ApiImplicitParam(name = "state", value = "2申请通过3驳回", required = true, paramType = "path")
    })
    public Result returnGoods(@PathVariable String orderId, @PathVariable Integer state) throws Exception {

        return  orderService.returnGoods(orderId,state,storeId);
    }
}
