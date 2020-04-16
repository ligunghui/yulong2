package com.jidu.service.impl;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.mapper.GoodsMapper;
import com.jidu.mapper.LocalServiceGoodsMapper;
import com.jidu.mapper.StoreRecommendGoodsMapper;
import com.jidu.pojo.StoreGongGroup;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.local.LocalServiceGoods;
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
 * Date: 2020/4/4 0004 上午 11:23
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
    private LocalServiceGoodsMapper localServiceGoodsMapper;

    @Override
    public List<StoreGongGroup> search(Map param, int status, Integer type, String storeId) {
        Example example = new Example(StoreRecommendGoods.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("apply_time DESC");
        if(0!=status){

            criteria.andEqualTo("state", status);
        }
        criteria.andEqualTo("type", type);
        criteria.andEqualTo("storeId", storeId);
        //1普通商品2本地服务类型商品
        List<StoreRecommendGoods> storeRecommendGoods = storeRecommendGoodsMapper.selectByExample(example);
        List<StoreGongGroup> storeGongGroups = new ArrayList<>();
        for (StoreRecommendGoods storeRecommendGood : storeRecommendGoods) {
            StoreGongGroup storeGongGroup = new StoreGongGroup();
            storeGongGroup.setId(storeRecommendGood.getId());
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

    @Override
    public Result add(Long goodsId, Integer type, String storeId) {
        //最多三条
        Example example = new Example(StoreRecommendGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        criteria.andNotEqualTo("state", 3);
        List<StoreRecommendGoods> list = storeRecommendGoodsMapper.selectByExample(example);
        if (list.size() >= 3) {
            return new Result(201, "每个商户最多推荐3种商品", false);
        }
        StoreRecommendGoods storeRecommendGoods = new StoreRecommendGoods();
        storeRecommendGoods.setApplyTime(new Date());
        storeRecommendGoods.setState(1);
        storeRecommendGoods.setGoodsId(goodsId);
        storeRecommendGoods.setType(type);
        storeRecommendGoods.setStoreId(storeId);
        storeRecommendGoodsMapper.insert(storeRecommendGoods);
        return new Result(ResultCode.SUCCESS);
    }
}
