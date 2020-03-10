package com.jidu.service.impl;

import com.jidu.mapper.ShoppingBannerMapper;
import com.jidu.pojo.shop.ShoppingBanner;
import com.jidu.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-17 15:18
 */
@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private ShoppingBannerMapper shoppingBannerMapper;
    @Override
    public List<ShoppingBanner> findBanner(String storeId,Integer location) {
        Example example = new Example(ShoppingBanner.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        if (null != location) {

            criteria.andEqualTo("location", location);
        }
        return shoppingBannerMapper.selectByExample(example);
    }
}
