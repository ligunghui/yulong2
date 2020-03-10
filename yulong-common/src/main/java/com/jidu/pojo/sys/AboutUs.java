package com.jidu.pojo.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: liguanghui
 * Date: 2020/3/6 0006 下午 2:50
 * @Version:
 * @Description:
 */

@Data
@Table(name = "about_us")
public class AboutUs {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "创建者")
    private String createBy;
    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "标记")
    private String remarks;
    @ApiModelProperty(value = "是否删除1是0否")
    private String delFlag;
    @ApiModelProperty(value = "标题")
    private String name;
    @ApiModelProperty(value = "内容")
    private String contents;
}
