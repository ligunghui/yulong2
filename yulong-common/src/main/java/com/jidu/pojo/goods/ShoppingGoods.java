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
 * @create: 2020-02-05 14:42
 */
@Data
@Table(name = "shopping_goods")
public class ShoppingGoods {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "添加时间")
    private Date addtime;
    @ApiModelProperty(value = "是否删除")
    private Boolean deletestatus;
    @ApiModelProperty(value = "详情")
    private String details;
    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "是否推荐到首页1是0否")
    private Integer homePage;
    @ApiModelProperty(value = "新人专区(1是0否)")
    private Integer isNew;
    @ApiModelProperty(value = "销售数量")
    private Integer saleNum;
    @ApiModelProperty(value = "剩余数量")
    private Integer surplusNum;
    @ApiModelProperty(value = "开始销售时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sellerTime;
    @ApiModelProperty(value = "运费")
    private BigDecimal goodsWeight;
    @ApiModelProperty(value = "商铺价格")
    private BigDecimal storePrice;
    @ApiModelProperty(value = "商户id")
    private String storeId;
    @ApiModelProperty(value = "商户名称")
    private String storeName;
    @ApiModelProperty(value = "市场价")
    private BigDecimal markePrice;
    @ApiModelProperty(value = "会员价")
    private BigDecimal memberPrice;
    @ApiModelProperty(value = "总量")
    private Integer totalNum;
    @ApiModelProperty(value = "0上架1下架")
    private Integer onsale;
    @ApiModelProperty(value = "类型id")
    private Integer typeId;
    @ApiModelProperty(value = "图片")
    private String img;

}
