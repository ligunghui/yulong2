package com.jidu.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.jidu.config.AlipayConfig;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.group.ConfirmOrder;
import com.jidu.mapper.UserInfoMapper;
import com.jidu.pojo.order.ShoppingOrder;
import com.jidu.pojo.sys.UserInfo;
import com.jidu.service.PayService;
import com.jidu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping({"/pay"})
@Api(value = "支付", description = "支付")
public class PayController  extends  BaseController{

    @Autowired
    private PayService payService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoMapper userInfoMapper;
    @ApiOperation("确认订单")
    @ApiImplicitParams({@ApiImplicitParam(name = "goodsId", value = "商品id", dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "num", value = "数量", dataType = "int", paramType = "path")})
    @RequestMapping(value = {"/confirmOrder/{goodsId}/{num}"}, method = {RequestMethod.GET})
    @ResponseBody
    public Result confirmOrder(@PathVariable String goodsId, @PathVariable Integer num) {
        ConfirmOrder confirmOrder= payService.confirmOrder(goodsId,num,userId);
        return new Result(ResultCode.SUCCESS,confirmOrder);
    }


    @ApiOperation("支付")
    @ApiImplicitParams({@ApiImplicitParam(name = "orderId", value = "订单id", dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "surplus", value = "是否余额抵扣(1是2否)", dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "type", value = "1支付宝2微信", dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "walletPassword", value = "钱包密码", dataType = "string", paramType = "query")})
    @RequestMapping(value = {"/goPay/{orderId}/{surplus}/{type}"}, method = {RequestMethod.GET})
    @ResponseBody
    public Result goPay(@PathVariable String orderId, @PathVariable Integer surplus, @RequestParam(required = false) String walletPassword, @PathVariable int type)
            throws Exception {
        Map map = new HashMap();
        ShoppingOrder order = payService.getOrderById(orderId);
        String userId = order.getUserId();
        BigDecimal totalprice = order.getTotalprice();
        UserInfo userInfo = userService.findById(userId);

        if (surplus.intValue() == 1) {
            if (StringUtils.isEmpty(walletPassword)) {
                return new Result(201, "交易密码不能为空", false);
            }
            if (StringUtils.isEmpty(userInfo.getWalletPassword())) {
                return new Result(201, "你还未设置交易密码", false);
            }
            walletPassword = new Md5Hash(walletPassword, userInfo.getMobile(), 3).toString();
            if (!walletPassword.equals(userInfo.getWalletPassword())) {
                return new Result(201, "交易密码错误", false);
            }

            if (userInfo.getWalletMoney().compareTo(totalprice) > -1) {
                BigDecimal subtract = userInfo.getWalletMoney().subtract(totalprice);
                userInfo.setWalletMoney(subtract);
                this.userInfoMapper.updateByPrimaryKeySelective(userInfo);
                return new Result(200, "余额充足", true);
            }

            surplus = 3;
        }


        if (surplus == 3) {
            totalprice = totalprice.subtract(userInfo.getWalletMoney());
            userInfo.setWalletMoney(new BigDecimal(0.0));
            this.userInfoMapper.updateByPrimaryKeySelective(userInfo);
        }
        double money = totalprice.doubleValue();
        if (1 == type) {

            return aliPay(money, orderId);
        } else {
            return payService. wxPay(totalprice.intValue(), orderId, "");
        }
    }

    public Result aliPay(double money, String orderId)
            throws UnsupportedEncodingException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, "RSA2");

        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();

        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
       /* if ("1".equals(type)) {
            model.setBody("智汇玉龙-充值");
        } else {
            model.setBody("智汇玉龙-订单");
        }*/
        model.setBody("智汇玉龙-订单");
        model.setSubject("智汇玉龙");
        model.setOutTradeNo(orderId);
        model.setTimeoutExpress("30m");

        model.setTotalAmount(money + "");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(AlipayConfig.notify_url);
        try {
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            return new Result(ResultCode.SUCCESS, response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return new Result(ResultCode.FAIL);
    }

    @RequestMapping(value = {"/alipayNotify"}, method = {RequestMethod.POST})
    private String alipayNotify(HttpServletRequest request, String out_trade_no) {
        try {
            Map params = new HashMap();
            Map requestParams = request.getParameterMap();
            System.out.println(requestParams);
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; ++i) {
                    valueStr = valueStr + values[i] + ",";
                }

                params.put(name, valueStr);
            }

            boolean flag = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, "RSA2");

            if (flag) {
                String types = (String) params.get("body");
                String[] split = types.split("-");
                String type = split[1];
                String payType="支付宝";
                System.out.println(type);
                payService.payok(out_trade_no, type,payType);
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("支付失败");
        return "fail";
    }

    @RequestMapping(value = {"/alipayReturn"}, method = {RequestMethod.POST})
    private Result alipayReturn(HttpServletRequest request, String out_trade_no, String trade_no, String total_amount)
            throws AlipayApiException {
        Map params = new HashMap();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; ++i) {
                valueStr = valueStr + values[i] + ",";
            }

            params.put(name, valueStr);
        }

        boolean flag = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, "RSA2");

        if (flag) {
            return new Result(ResultCode.SUCCESS);
        }
        return new Result(ResultCode.FAIL);
    }

    @ApiOperation("申请提现")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户id", dataType = "string", paramType = "path"), @ApiImplicitParam(name = "name", value = "真实姓名", dataType = "string", paramType = "path"), @ApiImplicitParam(name = "money", value = "金额", dataType = "string", paramType = "path"), @ApiImplicitParam(name = "sourceType", value = "贷款类型(名称)", dataType = "string", paramType = "path"), @ApiImplicitParam(name = "account", value = "提现账号", dataType = "string", paramType = "path"), @ApiImplicitParam(name = "type", value = "提现方式(1银行卡 2支付宝)", dataType = "string", paramType = "path")})
    @ResponseBody
    @RequestMapping(value = {"/applyWithdrawal/{userId}/{name}/{money}/{account}/{type}"}, method = {RequestMethod.GET})
    public Result applyWithdrawal(@PathVariable String userId, @PathVariable String name, @PathVariable BigDecimal money, @PathVariable String account, @PathVariable String type) {

        UserInfo userInfo = this.userService.findById(userId);
        return null;
    }

    @PostMapping("/wxPayNotify")
    @ApiOperation("微信回调")
    public String wxPayNotify(HttpServletRequest request) {
        System.out.println("=====微信开始回调==========");
        String resXml = "";
        try {
            InputStream inputStream = request.getInputStream();
            //将InputStream转换成xmlString
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            resXml = sb.toString();
            String result = payService.payBack(resXml);
            return result;
        } catch (Exception e) {
            System.out.println("微信手机支付失败:" + e.getMessage());
            String result = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            return result;
        }
    }
}