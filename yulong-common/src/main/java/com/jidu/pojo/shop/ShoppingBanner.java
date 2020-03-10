package com.jidu.pojo.shop;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Data
@Table(name = "shopping_banner")
public class ShoppingBanner {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    @ApiModelProperty(value = "图片地址")
    private String bannerimg;
    @ApiModelProperty(value = "是否删除")
    private Boolean deletestatus;
    @ApiModelProperty(value = "商户id")
    private String storeId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "添加时间")
    private Date addtime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "到期时间")
    private Date bannerdate;
    @ApiModelProperty(value = "0-----店铺发布，1----管理发")
    private Integer isAdminer;
    @ApiModelProperty(value = "位置1首页2智汇购")
    private  Integer location;

}