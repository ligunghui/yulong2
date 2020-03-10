package com.jidu.service;

import com.jidu.pojo.goods.GiftPack;

import java.util.List;
import java.util.Map;

public interface GiftPackService {
    void save(GiftPack giftPack);

    void update(GiftPack giftPack);

    GiftPack findById(long id);

    void delete(long id);

    List<GiftPack> search(Map param);
}
