package com.jidu.service;

import com.jidu.pojo.shop.ShoppingBanner;

import java.util.List;

public interface BannerService {
    void save(ShoppingBanner shoppingBanner);


    void delete(long id);

    List<ShoppingBanner> search();

    ShoppingBanner findById(long id);
}
