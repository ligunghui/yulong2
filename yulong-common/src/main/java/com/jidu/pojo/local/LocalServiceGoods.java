package com.jidu.pojo.local;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Author: liguanghui
 * Date: 2020/3/25 0025 下午 1:50
 * @Version:
 * @Description:
 */
@Data
@Table(name = "local_service_goods")
public class LocalServiceGoods {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    @ApiModelProperty(value = "主图")
    private String img;
    @ApiModelProperty(value = "介绍")
    private String introduce;
    @ApiModelProperty(value = "价格")
    private BigDecimal price;
    @ApiModelProperty(value = "单位")
    private String unit;
    @ApiModelProperty(value = "商户id")
    private String storeId;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "本地服务id")
    private Integer serviceId;
    @ApiModelProperty(value = "0=>首页 1=>非首页")
    private Integer homePage;

}
