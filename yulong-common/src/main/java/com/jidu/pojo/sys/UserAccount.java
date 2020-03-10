package com.jidu.pojo.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: liguanghui
 * Date: 2020/3/9 0009 下午 5:49
 * @Version:
 * @Description:
 */
@Data
@Table(name = "user_account")
public class UserAccount {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "用户id 商会id 商户id")
    private String itemId;
    @ApiModelProperty(value = "钱数")
    private BigDecimal money;
    @ApiModelProperty(value = "标识收入+花费-")
    private String symbol;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "行为")
    private String action;
}
