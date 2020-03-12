package com.jidu.service.impl;

import com.jidu.mapper.BannerMapper;
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
 * @create: 2020-02-06 11:18
 */
@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;
    @Override
    public void save(ShoppingBanner shoppingBanner) {
        bannerMapper.insert(shoppingBanner);
    }

    @Override
    public void update(ShoppingBanner shoppingBanner) {
        bannerMapper.updateByPrimaryKeySelective(shoppingBanner);
    }

    @Override
    public ShoppingBanner findById(long id) {
        return bannerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(long id) {
        bannerMapper.deleteByPrimaryKey(id);

    }

    @Override
    public List<ShoppingBanner> search(String storeId) {
        Example example=new Example(ShoppingBanner.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId",storeId);
        return bannerMapper.selectByExample(example);
    }
}
