package com.jidu.mapper;

import com.jidu.pojo.goods.ShoppingGoods;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: liguanghui
 * Date: 2020/3/10 0010 下午 3:21
 * @Version:
 * @Description:
 */
@Repository
public interface GoodsMapper extends Mapper<ShoppingGoods> {
}
