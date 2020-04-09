package com.jidu.pojo.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Author: liguanghui
 * Date: 2020/3/25 0025 下午 5:49
 * @Version:
 * @Description:
 */
@Data
@Table(name = "order_goods")
public class OrderGoods {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "商户信息")
    private String goodsId;
    @ApiModelProperty(value = "商品名")
    private String goodsName;
    @ApiModelProperty(value = "商品图")
    private String goodsImg;
    @ApiModelProperty(value = "商户信息")
    private BigDecimal goodsPrice;
    @ApiModelProperty(value = "商品数量")
    private Integer goodsNum;
    @ApiModelProperty(value = "0=>未售后 1=>售后中 3=>售后完成")
    private Integer isAfter;
    @ApiModelProperty(value = "用户id")
    private String uid;
    @ApiModelProperty(value = "订单id")
    private String orderId;
    @ApiModelProperty( value = "是否评价")
    private Integer isComment;
    @ApiModelProperty(value = "'1=> 普通商品 2=>礼包商品")
    private Integer type;

}
