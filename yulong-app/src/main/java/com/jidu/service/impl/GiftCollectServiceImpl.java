package com.jidu.service.impl;

import com.jidu.mapper.GiftCollectMapper;
import com.jidu.pojo.goods.GiftCollect;
import com.jidu.service.GiftCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-25 10:18
 */
@Service
public class GiftCollectServiceImpl implements GiftCollectService {
    @Autowired
    private GiftCollectMapper giftCollectMapper;
    @Override
    public void addGiftCollect(Long goodsId, String userId) {
        GiftCollect giftCollect=new GiftCollect();
        giftCollect.setGoodsId(goodsId);
        giftCollect.setUserId(userId);
        giftCollectMapper.insert(giftCollect);

    }

    @Override
    public void deleteGiftCollect(Long id) {
        giftCollectMapper.deleteByPrimaryKey(id);
    }
}
