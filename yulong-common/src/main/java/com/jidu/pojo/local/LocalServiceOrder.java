package com.jidu.pojo.local;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: liguanghui
 * Date: 2020/3/25 0025 下午 5:22
 * @Version:
 * @Description:
 */
@Data
@Table(name = "local_orders")
public class LocalServiceOrder  {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "订单号")
    private String orderSn;
    @ApiModelProperty(value = "服务名称")
    private String serviceName;
    @ApiModelProperty(value = "订单价格")
    private BigDecimal servicePrice;
    @ApiModelProperty(value = "订单时间")
    private Date time;
    @ApiModelProperty(value = "'0=>未支付 1=>待使用 2=>已使用")
    private int status;
    @ApiModelProperty(value = "服务商户")
    private String storeId;
    @ApiModelProperty(value = "服务商户")
    private String serviceId;
    @ApiModelProperty(value = "购买数量")
    private Integer serviceNum;
}
