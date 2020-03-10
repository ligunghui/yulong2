package com.jidu.service;

import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.pojo.shop.ShoppingStore;

import java.util.List;

public interface StoreService {

    void update(ShoppingStore shoppingStore);

    ShoppingStore findById(String id);

    void delete(String id);

    void updateBusinessAdmin(BusinessAdmin businessAdmin);

    void addBusinessAdmin(BusinessAdmin businessAdmin);

    List<BusinessAdmin> findBusinessAdmin(String storeId);

    BusinessAdmin findBusinessAdminById(int id);

    void deleteBusinessAdminById(int id);
}
