package com.jidu.pojo.shop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: liguanghui
 * Date: 2020/4/4 0004 上午 10:02
 * @Version:
 * @Description:
 */
@Data
@Table(name = "store_recommend_goods")
public class StoreRecommendGoods {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "商户id")
    private  String storeId;
    @ApiModelProperty(value = "商品id")
    private  Long goodsId;
    @ApiModelProperty(value = "申请时间")
    private Date applyTime;
    @ApiModelProperty(value = "处理时间")
    private Date handleTime;
    @ApiModelProperty(value = "1申请中2申请通过3驳回")
    private Integer state;
    @ApiModelProperty(value = "类型(1普通商品2本地服务类型商品)")
    private Integer type;
}
