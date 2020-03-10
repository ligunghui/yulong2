package com.jidu.pojo.goods;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-23 10:18
 */
@Data
@Table(name = "gift _pack")
public class GiftPack {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addtime;
    @ApiModelProperty(value = "是否删除")
    private Boolean deletestatus;
    @ApiModelProperty(value = "详情")
    private String details;
    @ApiModelProperty(value = "商品名称")
    private String name;
    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "销售数量")
    private Integer saleNum;
    @ApiModelProperty(value = "剩余数量")
    private Integer surplusNum;
    @ApiModelProperty(value = "开始销售时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sellerTime;
    @ApiModelProperty(value = "商铺价格")
    private BigDecimal storePrice;

    @ApiModelProperty(value = "市场价")
    private BigDecimal markePrice;
    @ApiModelProperty(value = "会员价")
    private BigDecimal memberPrice;

    @ApiModelProperty(value = "总量")
    private Integer totalNum;
    @ApiModelProperty(value = "0上架1下架")
    private Integer onsale;
    @ApiModelProperty(value = "图片")
    private String img;
    @ApiModelProperty(value = "运费")
    private BigDecimal postage;
}
