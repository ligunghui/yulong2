package com.jidu.service;

import com.jidu.pojo.goods.GiftPack;
import com.jidu.pojo.goods.GoodsType;
import com.jidu.pojo.goods.ShoppingGoods;

import java.util.List;

public interface PurchaseService {
    List<GoodsType> findGoodsType();

    List<ShoppingGoods> findGoods(Integer isNew);

    List<GiftPack> findGiftPack();

    GiftPack findGiftPackById(long id);
}
