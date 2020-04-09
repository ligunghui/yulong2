package com.jidu.pojo.goods;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: liguanghui
 * Date: 2020/3/23 0023 上午 10:15
 * @Version:
 * @Description:
 */

@Data
@Table(name = "goods_channel_type")
public class ChannelType {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer channelId;
    private Integer typeId;
    private String typeName;
    private String storeId;

}
