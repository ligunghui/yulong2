package com.jidu.pojo.card;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-12 10:38
 */
@Data
@Table(name = "electronic_card")
public class ElectronicCard {
    @Id
    private String userId;
    @ApiModelProperty(value = "真实姓名")
    private String trueName;
    @ApiModelProperty(value = "真实头像")
    private String trueImg;
    @ApiModelProperty(value = "公司")
    private String companyName;
    @ApiModelProperty(value = "职务")
    private String companyPost;
    @ApiModelProperty(value = "行业")
    private String industry;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "电话")
    private String mobile;
    @ApiModelProperty(value = "邮箱")
    private String mailbox;
    @ApiModelProperty(value = "座机")
    private String landline;
    @ApiModelProperty(value = "详情")
    private String details;
    @ApiModelProperty(value = "是否可见")
    private Boolean visible;
    @ApiModelProperty(value = "介绍")
    private String introduce;
}
