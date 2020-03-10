package com.jidu.service.impl;

import com.jidu.mapper.GiftPackMapper;
import com.jidu.mapper.GoodsTypeMapper;
import com.jidu.mapper.ShoppingGoodsMapper;
import com.jidu.pojo.goods.GiftPack;
import com.jidu.pojo.goods.GoodsType;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-22 15:32
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;
    @Autowired
    private ShoppingGoodsMapper shoppingGoodsMapper;
    @Autowired
    private GiftPackMapper giftPackMapper;

    @Override
    public List<GoodsType> findGoodsType() {
        Example example = new Example(GoodsType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", 0 + "");
        criteria.andEqualTo("type", 0);
        return goodsTypeMapper.selectByExample(example);
    }

    @Override
    public List<ShoppingGoods> findGoods(Integer isNew) {
        Example example = new Example(ShoppingGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isNew", isNew);
        example.setOrderByClause("addTime DESC");
        return shoppingGoodsMapper.selectByExample(example);
    }

    @Override
    public List<GiftPack> findGiftPack() {
        Example example = new Example(GiftPack.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("addTime DESC");
        return giftPackMapper.selectByExample(example);
    }

    @Override
    public GiftPack findGiftPackById(long id) {
        return giftPackMapper.selectByPrimaryKey(id);
    }
}
