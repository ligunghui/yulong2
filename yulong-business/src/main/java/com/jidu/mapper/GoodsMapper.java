package com.jidu.mapper;

import com.jidu.pojo.goods.ShoppingGoods;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface GoodsMapper extends Mapper<ShoppingGoods> {
}
