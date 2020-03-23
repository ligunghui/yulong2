package com.jidu.pojo.withdrawal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: liguanghui
 * Date: 2020/3/20 0020 上午 10:46
 * @Version:
 * @Description:
 */
@Data
@Table(name = "withdrawal_application")
public class WithdrawalApplication {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "用户id")
    private  String uid;
    @ApiModelProperty(value = "提现金额")
    private BigDecimal money;
    @ApiModelProperty(value = "1申请中2申请通过3驳回")
    private  Integer status;
    @ApiModelProperty(value = "驳回理由")
    private  String reason;
    @ApiModelProperty(value = "申请时间")
    private Date applyTime;
    @ApiModelProperty(value = "处理时间")
    private Date handleTime;
    @ApiModelProperty(value = "提现到1微信2支付宝")
    private  Integer type;
    @ApiModelProperty(value = "提现账户")
    private  String account;
    @ApiModelProperty(value = "用户名")
    private  String userName;
    @ApiModelProperty(value = "真实姓名")
    private  String trueName;
    @ApiModelProperty(value = "用户类型(1用户2商户3商会)")
    private  Integer userType;
}
