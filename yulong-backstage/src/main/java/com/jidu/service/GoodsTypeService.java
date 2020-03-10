package com.jidu.service;

import com.jidu.pojo.goods.GoodsType;

import java.util.List;
import java.util.Map;

public interface GoodsTypeService {
    void save(String s, GoodsType goodsType);

    void update(GoodsType goodsType);

    GoodsType findById(int id);

    void delete(int id);

    List<GoodsType> search(Map param);

    List<GoodsType> findOneLevel(String storeId, Integer parentId);
}
