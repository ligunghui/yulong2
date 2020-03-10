package com.jidu.service;

import com.jidu.pojo.goods.GoodsType;
import com.jidu.pojo.goods.ShoppingGoods;

import java.util.List;

public interface GoodsService {
    List<GoodsType> findGoodsTypeByStoreId(String storeId);

    List<ShoppingGoods> findGoodsByStoreIdAndType(String storeId, Integer type);
}
