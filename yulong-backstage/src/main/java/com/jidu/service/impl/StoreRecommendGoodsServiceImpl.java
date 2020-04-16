package com.jidu.service.impl;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.mapper.GoodsMapper;
import com.jidu.mapper.LocalServiceGoodsMapper;
import com.jidu.mapper.ShoppingStoreMapper;
import com.jidu.mapper.StoreRecommendGoodsMapper;
import com.jidu.pojo.StoreGongGroup;
import com.jidu.pojo.activity.ShoppingActivity;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.local.LocalServiceGoods;
import com.jidu.pojo.shop.ShoppingStore;
import com.jidu.pojo.shop.StoreRecommendGoods;
import com.jidu.service.StoreRecommendGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/4/4 0004 上午 10:11
 * @Version:
 * @Description:
 */
@Service
public class StoreRecommendGoodsServiceImpl implements StoreRecommendGoodsService {
    @Autowired
    private StoreRecommendGoodsMapper storeRecommendGoodsMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private ShoppingStoreMapper shoppingStoreMapper;
    @Autowired
    private LocalServiceGoodsMapper localServiceGoodsMapper;

    @Override
    public List<StoreGongGroup> search(Map param, Integer status, Integer type) {
        Example example = new Example(StoreRecommendGoods.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("apply_time DESC");
        if(0!=status){

            criteria.andEqualTo("state", status);
        }
        criteria.andEqualTo("type", type);

        //1普通商品2本地服务类型商品
        List<StoreRecommendGoods> storeRecommendGoods = storeRecommendGoodsMapper.selectByExample(example);
        List<StoreGongGroup> storeGongGroups = new ArrayList<>();
        for (StoreRecommendGoods storeRecommendGood : storeRecommendGoods) {
            StoreGongGroup storeGongGroup = new StoreGongGroup();
            storeGongGroup.setId(storeRecommendGood.getId());
            ShoppingStore shoppingStore = shoppingStoreMapper.selectByPrimaryKey(storeRecommendGood.getStoreId());
            storeGongGroup.setShoppingStore(shoppingStore);
            if (1 == type) {
                ShoppingGoods shoppingGoods = goodsMapper.selectByPrimaryKey(storeRecommendGood.getGoodsId());
                storeGongGroup.setShoppingGoods(shoppingGoods);
            } else {
                LocalServiceGoods localServiceGoods = localServiceGoodsMapper.selectByPrimaryKey(storeRecommendGood.getGoodsId());
                storeGongGroup.setLocalServiceGoods(localServiceGoods);
            }
            storeGongGroups.add(storeGongGroup);
        }
        return storeGongGroups;
    }

    @Override
    public Result verify(Integer id, Integer status) {
        StoreRecommendGoods storeRecommendGoods = storeRecommendGoodsMapper.selectByPrimaryKey(id);
        Integer type = storeRecommendGoods.getType();
        storeRecommendGoods.setState(status);
        storeRecommendGoods.setHandleTime(new Date());
        storeRecommendGoodsMapper.updateByPrimaryKey(storeRecommendGoods);
        if (2 == status) {
            if (1 == type) {
                ShoppingGoods shoppingGoods = goodsMapper.selectByPrimaryKey(storeRecommendGoods.getGoodsId());
                shoppingGoods.setHomePage(1);
                goodsMapper.updateByPrimaryKeySelective(shoppingGoods);
            } else {
                LocalServiceGoods localServiceGoods = localServiceGoodsMapper.selectByPrimaryKey(storeRecommendGoods.getGoodsId());
                localServiceGoods.setHomePage(0);
                localServiceGoodsMapper.updateByPrimaryKeySelective(localServiceGoods);
            }
        }
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public void withdraw(Integer id) {
        StoreRecommendGoods storeRecommendGoods = storeRecommendGoodsMapper.selectByPrimaryKey(id);
        if (storeRecommendGoods.getState() == 2) {
            Integer type = storeRecommendGoods.getType();
            if (1 == type) {
                ShoppingGoods shoppingGoods = goodsMapper.selectByPrimaryKey(storeRecommendGoods.getGoodsId());
                shoppingGoods.setHomePage(0);
                goodsMapper.updateByPrimaryKeySelective(shoppingGoods);
            } else {
                LocalServiceGoods localServiceGoods = localServiceGoodsMapper.selectByPrimaryKey(storeRecommendGoods.getGoodsId());
                localServiceGoods.setHomePage(1);

                localServiceGoodsMapper.updateByPrimaryKeySelective(localServiceGoods);
            }
        }
        storeRecommendGoodsMapper.deleteByPrimaryKey(id);
    }
}
