package com.jidu.pojo.address;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-16 12:35
 */
@Data
@Table(name = "shopping_address")
public class ShoppingAddress {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "添加时间")
    private Date addtime;
    @ApiModelProperty(value = "手机")
    private String mobile;
    @ApiModelProperty(value = "真实姓名")
    private String truename;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "0.非默认地址 1.默认地址")
    private Integer type;
    @ApiModelProperty(value = "邮编")
    private String code;
    @ApiModelProperty(value = "详情地址")
    private String areaInfo;
}
