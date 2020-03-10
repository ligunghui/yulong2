package com.jidu.pojo.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/5 0005 下午 5:29
 * @Version:
 * @Description:
 */
@Data
public class StatisticsGroup {
    @ApiModelProperty(value = "最近七天下单")
    private List<SevenOrder> sevenOrders;
    @ApiModelProperty(value = "今日订单数")
    private int NowOrders;
    @ApiModelProperty(value = "待发货数")
    private int shipped;
    @ApiModelProperty(value = "退货申请数")
    private int returnGoods;
    @ApiModelProperty(value = "登录名")
    private String userName;
    @ApiModelProperty(value = "缺货产品数")
    private int lessGoods;
    @ApiModelProperty(value = "新增产品数")
    private int newGoods;
    @ApiModelProperty(value = "产品总数")
    private int goodsTotal;
}
