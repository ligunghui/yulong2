package com.jidu.service;

import com.jidu.entity.Result;
import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.pojo.shop.ShoppingStore;

import java.util.List;
import java.util.Map;

public interface StoreService {
    List<ShoppingStore> search(Map param, int type, Integer storeType);

    ShoppingStore findShoppingStoreById(String id);

    Result verify(String id, String violationReseaon, int storeStatus);
     List<BusinessAdmin> findBusinessAdminByUserName(String username);
}
