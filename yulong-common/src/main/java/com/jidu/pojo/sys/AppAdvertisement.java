package com.jidu.pojo.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: liguanghui
 * Date: 2020/3/10 0010 下午 6:16
 * @Version:
 * @Description:
 */
@Data
@Table(name = "app_advertisement")
public class AppAdvertisement {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "图片")
    private String img;
    @ApiModelProperty(value = "1图片2视频")
    private int type;
    @ApiModelProperty(value = "1是2否")
    private int isShow;
}
