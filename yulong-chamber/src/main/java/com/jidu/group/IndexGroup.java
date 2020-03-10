package com.jidu.group;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: liguanghui
 * Date: 2020/3/9 0009 下午 6:56
 * @Version:
 * @Description:
 */
@Data
public class IndexGroup {
    @ApiModelProperty(value = "本月收益")
    private BigDecimal monthProfit;
    @ApiModelProperty(value = "历史收益")
    private BigDecimal historyProfit;
    @ApiModelProperty(value = "提现")
    private BigDecimal withdrawal;
    @ApiModelProperty(value = "推荐商户数")
    private Integer recommend;
}
