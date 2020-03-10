package com.jidu.service;

import com.jidu.pojo.shop.ShoppingStore;

import java.util.List;
import java.util.Map;

public interface StoreService {
    List<ShoppingStore> search(Map param, int type);

    ShoppingStore findShoppingStoreById(String id);

    void verify(String id, String violationReseaon, int storeStatus);
}
