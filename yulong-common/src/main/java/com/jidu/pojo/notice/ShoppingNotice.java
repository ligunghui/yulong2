package com.jidu.pojo.notice;

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
 * @create: 2020-02-10 16:36
 */
@Data
@Table(name = "shopping_notice")
public class ShoppingNotice {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "消息的类型(1系统消息 2平台公告 3商会公告 4商户公告)")
    private Integer type;
    @ApiModelProperty(value = "是否显示(0是1否)")
    private Integer display;
    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addtime;
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @ApiModelProperty(value = "创建用户名")
    private String createName;
    @ApiModelProperty(value = "创建者id")
    private String createId;
    @ApiModelProperty(value = "商户id")
    private String storeId;
    @ApiModelProperty(value = "显示位置")
    private String position;
}
