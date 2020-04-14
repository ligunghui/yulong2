package com.jidu.pojo.shop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Author: liguanghui
 * Date: 2020/4/14 0014 上午 9:00
 * @Version:
 * @Description:
 */
@Data
@Table(name = "shopping_guige")
public class ShoppingGuige {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "商品id")
    private String goodsId;
    @ApiModelProperty(value = "规格名称")
    private String name;
    @ApiModelProperty(value = "原价")
    private BigDecimal marketPrice;
    @ApiModelProperty(value = "会员价")
    private BigDecimal memberPrice;
    @ApiModelProperty(value = "商品库存")
    private String storage;

}
