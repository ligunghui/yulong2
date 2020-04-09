package com.jidu.pojo.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Author: liguanghui
 * Date: 2020/3/19 0019 下午 2:52
 * @Version:
 * @Description:
 */
@Data
@Table(name = "integral_rule")
public class IntegralRule {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "购买商品奖励(%)")
    private BigDecimal purchaseGoods;
    @ApiModelProperty(value = "邀请注册(个)")
    private Integer register;
    @ApiModelProperty(value = "分享商品(个)")
    private Integer shareGoods;
    @ApiModelProperty(value = "玉龙卡(个)")
    private Integer vipCard;
}
