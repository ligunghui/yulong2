package com.jidu.service.impl;

import com.jidu.mapper.GiftPackMapper;
import com.jidu.pojo.goods.GiftPack;
import com.jidu.service.GiftPackService;
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
 * @create: 2020-02-23 10:24
 */
@Service
public class GiftPackServiceImpl implements GiftPackService {
    @Autowired
    private GiftPackMapper giftPackMapper;

    @Override
    public void save(GiftPack giftPack) {
        giftPack.setAddtime(new Date());
        giftPack.setDeletestatus(false);
        giftPack.setOnsale(0);
        giftPack.setSurplusNum(giftPack.getTotalNum());
        giftPackMapper.insert(giftPack);

    }

    @Override
    public void update(GiftPack giftPack) {
        giftPackMapper.updateByPrimaryKeySelective(giftPack);
    }

    @Override
    public GiftPack findById(long id) {
        return giftPackMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(long id) {
        GiftPack giftPack = giftPackMapper.selectByPrimaryKey(id);
        giftPack.setDeletestatus(true);
        giftPackMapper.updateByPrimaryKey(giftPack);
    }

    @Override
    public List<GiftPack> search(Map param) {
        Example example = new Example(GiftPack.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deletestatus", false);
        return giftPackMapper.selectByExample(example);
    }
}
