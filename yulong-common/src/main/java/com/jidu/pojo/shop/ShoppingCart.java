package com.jidu.pojo.shop;

import com.jidu.pojo.order.OrderItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-13 14:02
 */
@Data
public class ShoppingCart implements Serializable {
    @ApiModelProperty(value = "商户id")
    private  String storeId;
    @ApiModelProperty(value = "商户名称")
    private  String storeName;
    @ApiModelProperty(value = "订单明细")
    private List<OrderItem> OrderItemList;
}
