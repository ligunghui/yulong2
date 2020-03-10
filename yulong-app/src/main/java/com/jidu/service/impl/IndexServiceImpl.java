package com.jidu.service.impl;

import com.jidu.mapper.BusinessOpportunitiesMapper;
import com.jidu.mapper.ShoppingActivityMapper;
import com.jidu.mapper.ShoppingGoodsMapper;
import com.jidu.pojo.activity.ShoppingActivity;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.opportunities.BusinessOpportunities;
import com.jidu.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-17 15:10
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private ShoppingActivityMapper activityMapper;
    @Autowired
    private ShoppingGoodsMapper shoppingGoodsMapper;
    @Autowired
    private BusinessOpportunitiesMapper businessOpportunitiesMapper;

    @Override
    public List<ShoppingActivity> findActivityByType(String type) {
        Example example = new Example(ShoppingActivity.class);
        Example.Criteria criteria = example.createCriteria();
        if ("1".equals(type)) {

            criteria.andEqualTo("type", "1");
        } else {
            criteria.andNotEqualTo("type", "1");
        }
        example.setOrderByClause("addtime DESC");
        criteria.andEqualTo("homePage", 1);
        return activityMapper.selectByExample(example);
    }

    @Override
    public List<ShoppingGoods> findGoods() {
        Example example = new Example(ShoppingGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("homePage",1);
        example.setOrderByClause("addTime DESC");
        return shoppingGoodsMapper.selectByExample(example);
    }

    @Override
    public List<BusinessOpportunities> findOpportunities() {
        Example example = new Example(BusinessOpportunities.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("addTime DESC");
        return businessOpportunitiesMapper.selectByExample(example);
    }
}
