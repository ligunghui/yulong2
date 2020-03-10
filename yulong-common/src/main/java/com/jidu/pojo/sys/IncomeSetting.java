package com.jidu.pojo.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Author: liguanghui
 * Date: 2020/3/9 0009 下午 1:53
 * @Version:
 * @Description:
 */
@Data
@Table(name = "income_setting")
public class IncomeSetting {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "购买玉龙卡商会奖励")
    private BigDecimal vipChamber;
    @ApiModelProperty(value = "购买玉龙卡推荐人奖励")
    private BigDecimal vipRecommend;
    @ApiModelProperty(value = "交易商会奖励")
    private Double transactionChamber;
    @ApiModelProperty(value = "交易平台奖励")
    private Double transactionPlatform;
    @ApiModelProperty(value = "提现手续费")
    private Double serviceCharge;
}
