package com.jidu.pojo.goods;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "gift_collect")
public class GiftCollect {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String userId;
    private Long goodsId;
}
