package com.jidu.service.impl;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.mapper.GoodsMapper;
import com.jidu.mapper.StoreRecommendGoodsMapper;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.shop.StoreRecommendGoods;
import com.jidu.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-05 14:50
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private StoreRecommendGoodsMapper storeRecommendGoodsMapper;

    @Override
    public void save(ShoppingGoods shoppingGoods) {
        shoppingGoods.setAddtime(new Date());
        //System.out.println(shoppingGoods.getTotalNum());
        shoppingGoods.setSurplusNum(shoppingGoods.getTotalNum());
        shoppingGoods.setSaleNum(0);
        shoppingGoods.setDeletestatus(false);
        goodsMapper.insert(shoppingGoods);
    }

    @Override
    public void update(ShoppingGoods shoppingGoods) {
        goodsMapper.updateByPrimaryKeySelective(shoppingGoods);
    }

    @Override
    public ShoppingGoods findById(long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public Result delete(long id) {
        Example example = new Example(StoreRecommendGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodsId", id);
        List<StoreRecommendGoods> storeRecommendGoods = storeRecommendGoodsMapper.selectByExample(example);
        if (!storeRecommendGoods.isEmpty()) {

            return new Result(201, "您已经推荐过该商品,请先撤回推荐!", false);
        }

        ShoppingGoods shoppingGoods = new ShoppingGoods();
        shoppingGoods.setId(id);
        shoppingGoods.setDeletestatus(true);
        goodsMapper.updateByPrimaryKeySelective(shoppingGoods);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public List<ShoppingGoods> search(Map param, String storeId) {
        Example example = new Example(ShoppingGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        criteria.andEqualTo("deletestatus", false);
        return goodsMapper.selectByExample(example);
    }
}
