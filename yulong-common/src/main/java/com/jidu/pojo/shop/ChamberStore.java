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
 * @create: 2020-02-10 10:47
 */
@Data
@Table(name = "chamber_store")
public class ChamberStore {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "商会id")
    private Integer chamberId;
    @ApiModelProperty(value = "商户id")
    private String storeId;
    @ApiModelProperty(value = "是否推荐(1是0否)")
    private Integer recommend;
    @ApiModelProperty(value = "二维码")
    private String code;
}
