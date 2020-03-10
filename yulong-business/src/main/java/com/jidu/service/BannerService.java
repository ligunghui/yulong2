package com.jidu.service;

import com.jidu.pojo.shop.ShoppingBanner;

import java.util.List;

public interface BannerService {
    void save(ShoppingBanner shoppingBanner);

    void update(ShoppingBanner shoppingBanner);

    ShoppingBanner findById(long id);

    void delete(long id);

    List<ShoppingBanner> search(String storeId);
}
