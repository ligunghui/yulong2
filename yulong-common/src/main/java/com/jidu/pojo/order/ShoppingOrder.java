package com.jidu.pojo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
@Data
@Table(name = "shopping_order")
public class ShoppingOrder {
    @Id
    private String id;
    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addtime;
    @ApiModelProperty(value = "订单id")
    private String orderId;
    @ApiModelProperty(value = "(0待支付1待发货2待收货3待评价4申请退货5已退货)")
    private Integer orderStatus;
    @ApiModelProperty(value = "支付时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date paytime;
    @ApiModelProperty(value = "买家留言")
    private String payMsg;
    @ApiModelProperty(value = "卖家留言")
    private String sellerMsg;
    @ApiModelProperty(value = "快递号")
    private String shipcode;
    @ApiModelProperty(value = "发货时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date shiptime;
    @ApiModelProperty(value = "快递费")
    private BigDecimal shipPrice;
    @ApiModelProperty(value = "总价格")
    private BigDecimal totalprice;
    @ApiModelProperty(value = "地址id")
    private Long addrId;
    @ApiModelProperty(value = "商户id")
    private String storeId;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "是否自动确认")
    private Boolean autoConfirmEmail;
    @ApiModelProperty(value = "")
    private Boolean autoConfirmSms;
    @ApiModelProperty(value = "单号")
    private String transport;
    @ApiModelProperty(value = "退货单号")
    private String returnShipcode;
    @ApiModelProperty(value = "退货原因")
    private String returnContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "退货时间")
    private Date returnTime;
    @ApiModelProperty(value = "订单类型(0商户 1平台)")
    private Integer orderType;
    @ApiModelProperty(value = "完成时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishtime;
    @ApiModelProperty(value = "商品id")
    private String goodsId;
    @ApiModelProperty(value = "数量")
    private Integer num;
}