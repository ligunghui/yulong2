package com.jidu.pojo.goods;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "goods_type")
public class GoodsType {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "商铺id 平台0")
    private String storeId;

    @ApiModelProperty(value = "上级分类的id")
    private Integer parentId;


}