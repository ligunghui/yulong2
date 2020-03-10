package com.jidu.pojo.shop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-26 10:55
 */
@Data
@Table(name = "business_admin")
public class BusinessAdmin {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "商户id")
    private String storeId;
    @ApiModelProperty(value = "(是否可用1是0否)")
    private int useable;
    @ApiModelProperty(value = "0平台总管理员1平台管理2商会管理3商户管理")
    private int type;

}
