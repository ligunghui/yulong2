package com.jidu.pojo.shop;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-06 16:03
 */
@Data
@Table(name = "shopping_store")
public class ShoppingStore {
    @Id
    private String id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addtime;
    @ApiModelProperty(value = "商户地址")
    private String storeAddress;
    @ApiModelProperty(value = "商户信用值")
    private Integer storeCredit;
    @ApiModelProperty(value = "商户名称")
    private String storeName;
    @ApiModelProperty(value = "商户店家")
    private String storeOwer;
    @ApiModelProperty(value = "商户店家id")
    private String owerId;
    @ApiModelProperty(value = "qq")
    private String storeQq;
    @ApiModelProperty(value = "是否推荐")
    private Boolean storeRecommend;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "推荐时间")
    private Date storeRecommendTime;
    @ApiModelProperty(value = "商户状态申请中2申请通过3申请不通过4关闭")
    private Integer storeStatus;
    @ApiModelProperty(value = "电话")
    private String storeTelephone;
    @ApiModelProperty(value = "是否审核1是0否")
    private Integer validity;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "审核时间")
    private Date validityTime;
    @ApiModelProperty(value = "log")
    private String logo;
    @ApiModelProperty(value = "所属商会id")
    private Integer chamberId;
    @ApiModelProperty(value = "商户信息")
    private String storeInfo;
    @ApiModelProperty(value = "商户信息")
    private String storeDescription;
    @ApiModelProperty(value = "关键字")
    private String storeKeywords;
    @ApiModelProperty(value = "拒绝原因")
    private String violationReseaon;
    @ApiModelProperty(value = "总钱数")
    private BigDecimal totalMoney;
}
