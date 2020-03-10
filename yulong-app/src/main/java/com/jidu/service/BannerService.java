package com.jidu.service;

import com.jidu.pojo.shop.ShoppingBanner;

import java.util.List;

public interface BannerService {
    List<ShoppingBanner> findBanner(String storeId, Integer location);
}
