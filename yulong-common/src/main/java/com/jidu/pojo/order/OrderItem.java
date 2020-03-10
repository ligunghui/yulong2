package com.jidu.pojo.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-13 15:41
 */
@Data
public class OrderItem implements Serializable {
    private Long id;
    @ApiModelProperty(value = "id")
    private Long itemId;
    @ApiModelProperty(value = "商品id")
    private Long goodsId;
    @ApiModelProperty(value = "订单id")
    private Long orderId;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "价格")
    private BigDecimal price;
    @ApiModelProperty(value = "数量")
    private Integer num;
    @ApiModelProperty(value = "运费")
    private BigDecimal totalFee;
    @ApiModelProperty(value = "图片")
    private String picPath;
    @ApiModelProperty(value = "卖家id")
    private String sellerId;
}
