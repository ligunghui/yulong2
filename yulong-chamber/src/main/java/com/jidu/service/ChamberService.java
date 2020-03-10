package com.jidu.service;

import com.jidu.entity.Result;
import com.jidu.pojo.shop.ChamberStore;
import com.jidu.pojo.shop.ShoppingChamber;
import com.jidu.pojo.shop.ShoppingStore;

import java.util.List;
import java.util.Map;

public interface ChamberService {
    void save(ShoppingChamber shoppingChamber);

    void update(ShoppingChamber shoppingChamber);

    ShoppingChamber findById(Integer id);

    void delete(Integer id);

    List<ShoppingChamber> search(Map param);

    Result recommend(Integer chamberId, String storeId);

    Result cancelRecommend(Integer chamberId, String storeId);

    List<ChamberStore> searchRecommend(Map param, Integer chamberId);

    List<ShoppingStore> noRecommend();

    ShoppingStore findByShoppingStoreById(String storeId);
}
