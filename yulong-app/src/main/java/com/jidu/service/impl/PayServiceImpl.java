package com.jidu.service.impl;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.jidu.config.WXConfigUtil;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.group.ConfirmOrder;
import com.jidu.mapper.ShoppingGoodsMapper;
import com.jidu.mapper.ShoppingOrderMapper;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.order.ShoppingOrder;
import com.jidu.service.PayService;
import com.jidu.utils.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-23 14:53
 */
@Service
public class PayServiceImpl implements PayService {
    private final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);
    @Autowired
    private ShoppingOrderMapper shoppingOrderMapper;
    @Autowired
    private ShoppingGoodsMapper shoppingGoodsMapper;
    @Autowired
    private IdWorker idWorker;
    private String notify_url;

    @Override
    public ConfirmOrder confirmOrder(String goodsId, Integer num, String userId) {
        ShoppingGoods shoppingGoods = shoppingGoodsMapper.selectByPrimaryKey(goodsId);
        ShoppingOrder order = new ShoppingOrder();
        String id = idWorker.nextId() + "";
        order.setId(id);
        order.setAddtime(new Date());
        order.setNum(num);
        order.setGoodsId(goodsId);
        order.setOrderId(id);
        order.setOrderStatus(0);
        BigDecimal goodsWeight = shoppingGoods.getGoodsWeight();
        order.setShipPrice(goodsWeight);
        order.setUserId(userId);
        order.setStoreId(shoppingGoods.getStoreId());
        BigDecimal multiply = shoppingGoods.getStorePrice().multiply(new BigDecimal(num));
        order.setTotalprice(multiply.add(goodsWeight));
        shoppingOrderMapper.insert(order);
        ConfirmOrder confirmOrder = new ConfirmOrder();
        confirmOrder.setShoppingGoods(shoppingGoods);
        confirmOrder.setShoppingOrder(order);
        return confirmOrder;
    }

    /**
     * @param out_trade_no 订单id
     * @param type         购买商品 或者其他
     * @param payType      支付方式
     */
    @Override
    public void payok(String out_trade_no, String type, String payType) {
            //如果是商品购买 需要同时保存用户 商家 收支明细

    }

    @Override
    public ShoppingOrder getOrderById(String orderId) {
        return shoppingOrderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public Result wxPay(int total_fee, String out_trade_no, String type) {
        Map resultMap = new HashMap();
        WXConfigUtil config = null;
        WXPay wxpay = null;
        try {
            config = new WXConfigUtil();
            wxpay = new WXPay(config);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String nonce_str = WXPayUtil.generateNonceStr();

        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String spbill_create_ip = addr.getHostAddress();

        String body = type;

        HashMap data = new HashMap();
        data.put("appid", config.getAppID());
        data.put("mch_id", config.getMchID());
        data.put("nonce_str", nonce_str);
        data.put("body", body);
        data.put("out_trade_no", out_trade_no);
        data.put("total_fee", String.valueOf(total_fee));
        data.put("spbill_create_ip", spbill_create_ip);
        data.put("notify_url", "http://qingmang.ijidoo.cn/pay/wxNotify");
        data.put("trade_type", "APP");
        data.put("attach", type);
        try {
            String sign = WXPayUtil.generateSignedXml(data, config.getKey());
            data.put("sign", sign);
            Map rMap = wxpay.unifiedOrder(data);
            System.out.println(new StringBuilder().append("统一下单接口返回: ").append(rMap).toString());
            String return_code = (String) rMap.get("return_code");
            String result_code = (String) rMap.get("result_code");
            String nonceStr = WXPayUtil.generateNonceStr();
            resultMap.put("nonceStr", nonceStr);
            Long timeStamp = Long.valueOf(System.currentTimeMillis() / 1000L);
            if (("SUCCESS".equals(return_code)) && (return_code.equals(result_code))) {
                Long time = System.currentTimeMillis() / 1000;
                String timestamp = time.toString();
                resultMap.put("appid", config.getAppID());
                resultMap.put("partnerid", config.getMchID());
                resultMap.put("prepayid", rMap.get("prepay_id"));
                resultMap.put("noncestr", rMap.get("nonce_str"));
                resultMap.put("timestamp", timestamp);
                resultMap.put("package", "Sign=WXPay");
                String sign1 = WXPayUtil.generateSignature(resultMap, config.getKey());
                resultMap.put("paySign", sign1);
                System.out.println(new StringBuilder().append("生成的签名paySign : ").append(sign1).toString());

                return new Result(ResultCode.SUCCESS, resultMap);
            }
            return new Result(2003, "支付失败", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(2003, "支付失败", false);
    }


    @Override
    public String payBack(String resXml) {
        WXConfigUtil config = null;
        try {
            config = new WXConfigUtil();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WXPay wxpay = new WXPay(config);
        String xmlBack = "";
        Map<String, String> notifyMap = null;
        try {
            notifyMap = WXPayUtil.xmlToMap(resXml);
            if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
                String return_code = notifyMap.get("return_code");
                String out_trade_no = notifyMap.get("out_trade_no");
                if (return_code.equals("SUCCESS")) {
                    if (out_trade_no != null) {
                        logger.info("微信手机支付回调成功订单号:{}", out_trade_no);
                        xmlBack = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    } else {
                        logger.info("微信手机支付回调失败订单号:{}", out_trade_no);
                        xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                    }
                }
                return xmlBack;
            } else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                //失败的数据要不要存储？
                logger.error("手机支付回调通知签名错误");
                xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                return xmlBack;
            }
        } catch (Exception e) {
            logger.error("手机支付回调通知失败", e);
            xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        return xmlBack;
    }


}
