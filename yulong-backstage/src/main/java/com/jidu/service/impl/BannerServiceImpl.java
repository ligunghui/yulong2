package com.jidu.service.impl;

import com.jidu.mapper.ShoppingBannerMapper;
import com.jidu.pojo.shop.ShoppingBanner;
import com.jidu.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-25 15:40
 */
@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private ShoppingBannerMapper shoppingBannerMapper;

    @Override
    public void save(ShoppingBanner shoppingBanner) {
        shoppingBanner.setStoreId("0");
        shoppingBanner.setAddtime(new Date());
        shoppingBanner.setIsAdminer(1);
        shoppingBannerMapper.insert(shoppingBanner);
    }

    @Override
    public void delete(long id) {
        shoppingBannerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<ShoppingBanner> search() {
        return shoppingBannerMapper.selectAll();
    }
}
