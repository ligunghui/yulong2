package com.jidu.pojo.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "user_info")
@ApiModel(value = "user对象", description = "用户对象")
public class UserInfo {
    @Id
    private String id;
    //@Column(name = "f_user_id")
    @ApiModelProperty(value = "添加时间")
    private Date addtime;
    @ApiModelProperty(value = "是否删除")
    private Boolean deletestatus;
    @ApiModelProperty(value = "qq")
    private String qq;
    @ApiModelProperty(value = "微信")
    private String wx;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "生日")
    private Date birthday;
    @ApiModelProperty(value = "email")
    private String email;
    @ApiModelProperty(value = "最后登录时间")
    private Date lastlogindate;
    @ApiModelProperty(value = "最后登录ip")
    private String lastloginip;
    @ApiModelProperty(value = "登录次数")
    private Integer logincount;

    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "座机")
    private String phone;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "钱包密码")
    private String walletPassword;
    @ApiModelProperty(value = "钱数")
    private BigDecimal walletMoney;
    @ApiModelProperty(value = "性别")
    private Integer sex;
    @ApiModelProperty(value = "真实姓名")
    private String truename;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "角色")
    private String userrole;
    @ApiModelProperty(value = "信用值")
    private Integer userCredit;
    @ApiModelProperty(value = "商户id")
    private String storeId;
    @ApiModelProperty(value = "qqOpenid")
    private String qqOpenid;
    @ApiModelProperty(value = "sinaOpenid")
    private String sinaOpenid;
    @ApiModelProperty(value = "上级id")
    private String parentId;
    @ApiModelProperty(value = "头像")
    private String userphoto;
    @ApiModelProperty(value = "类型")
    private String usertype;
    @ApiModelProperty(value = "wxopenid")
    private String wxopenid;
    @ApiModelProperty(value = "商会id")
    private Integer chamberId;
    @ApiModelProperty(value = "邀请码")
    private String invitationCode;
    @ApiModelProperty(value = "身份证号")
    private String certificate;
    @ApiModelProperty(value = "身份证正面")
    private String cardImg;
    @ApiModelProperty(value = "身份证反面")
    private String reverseImg;
    @ApiModelProperty(value = "是否实名认证")
    private int authentication;
    @ApiModelProperty(value = "等级id")
    private int gradeId;
    @ApiModelProperty(value = "玉龙卡开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date vipStart;
    @ApiModelProperty(value = "玉龙卡结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date vipEnd;
    @ApiModelProperty(value = "是否购买玉龙卡(1是0否)")
    private int vipIs;
    @ApiModelProperty(value = "积分")
    private int integral;

}