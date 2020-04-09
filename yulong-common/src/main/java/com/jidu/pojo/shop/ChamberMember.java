package com.jidu.pojo.shop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: liguanghui
 * Date: 2020/4/2 0002 上午 9:04
 * @Version:
 * @Description:
 */
@Data
@Table(name = "chamber_member")
public class ChamberMember {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "商会ID")
    private String chamberId;
    @ApiModelProperty(value = "成员姓名")
    private String userName;
    @ApiModelProperty(value = "手机号")
    private String userPhone;
    @ApiModelProperty(value = "商会职称（如：会长/副会长）")
    private String chamberTitle;
    @ApiModelProperty(value = "成员邮箱")
    private String userMail;
    @ApiModelProperty(value = "成员头像")
    private String userAvatar;
    @ApiModelProperty(value = "所在行业")
    private String userIndustry;
    @ApiModelProperty(value = "公司名称")
    private String userCompany;
    @ApiModelProperty(value = "介绍")
    private String userIntro;
    @ApiModelProperty(value = "地址")
    private String userAddress;
    @ApiModelProperty(value = "添加时间")
    private Date addtime;

}
