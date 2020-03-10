package com.jidu.pojo.shop;

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
 * @create: 2020-02-10 10:49
 */
@Data
@Table(name = "shopping_chamber")
public class ShoppingChamber {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "商会名称")
    private String name;
    @ApiModelProperty(value = "商会地址")
    private String address;
    @ApiModelProperty(value = "商会log")
    private String log;
    @ApiModelProperty(value = "商会头像")
    private String img;
    @ApiModelProperty(value = "商会介绍")

    private String introduction;
    @ApiModelProperty(value = "状态(1申请中2申请通过3申请不通过4关闭)")
    private Integer status;
    @ApiModelProperty(value = "是否显示在首页1是0否")
    private Integer display;
    @ApiModelProperty(value = "拒绝理由")
    private String violationReseaon;
    @ApiModelProperty(value = "审核时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date validityTime;
}
