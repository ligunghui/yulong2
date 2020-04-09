package com.jidu.pojo.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: liguanghui
 * Date: 2020/3/6 0006 下午 6:23
 * @Version:
 * @Description:
 */
@Data
@Table(name = "sys_feedback")
public class SysFeedback {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "用户")
    private String userid;
    @ApiModelProperty(value = "内容")
    private String contents;
    @ApiModelProperty(value = "图片数组")
    private String imgs;
    @ApiModelProperty(value = "添加时间")
    private Date createDate;

}
