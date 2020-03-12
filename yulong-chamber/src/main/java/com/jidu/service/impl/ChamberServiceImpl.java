package com.jidu.service.impl;

import com.jidu.entity.Result;
import com.jidu.mapper.ChamberMapper;
import com.jidu.mapper.ChamberStoreMapper;
import com.jidu.mapper.ShoppingStoreMapper;
import com.jidu.pojo.shop.ChamberStore;
import com.jidu.pojo.shop.ShoppingChamber;
import com.jidu.pojo.shop.ShoppingStore;
import com.jidu.service.ChamberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-10 11:02
 */
@Service
public class ChamberServiceImpl implements ChamberService {
    @Autowired
    private ChamberMapper chamberMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ChamberStoreMapper chamberStoreMapper;
    @Autowired
    private ShoppingStoreMapper shoppingStoreMapper;


    @Override
    public void save(ShoppingChamber shoppingChamber) {
        chamberMapper.insert(shoppingChamber);
    }

    @Override
    public void update(ShoppingChamber shoppingChamber) {
        chamberMapper.updateByPrimaryKeySelective(shoppingChamber);
    }

    @Override
    public ShoppingChamber findById(Integer id) {
        return chamberMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
        chamberMapper.deleteByPrimaryKey(id);

    }

    @Override
    public List<ShoppingChamber> search(Map param) {
        Example example = new Example(ShoppingChamber.class);
        Example.Criteria criteria = example.createCriteria();
        return chamberMapper.selectByExample(example);
    }

    @Override
    public Result recommend(Integer chamberId, String storeId) {
        ShoppingStore shoppingStore = shoppingStoreMapper.selectByPrimaryKey(storeId);
        if (Objects.isNull(shoppingStore)) {
            return new Result(100, "商户为空", false);
        }
        if (shoppingStore.getStoreRecommend()) {
            return new Result(100, "商户已经其他被推荐", false);
        }

        ChamberStore chamberStore = new ChamberStore();
        chamberStore.setChamberId(chamberId);
        chamberStore.setStoreId(storeId);
        ChamberStore selectOne = chamberStoreMapper.selectOne(chamberStore);
        if (Objects.isNull(selectOne)) {

            chamberStore.setRecommend(1);
            chamberStoreMapper.insert(chamberStore);
        } else {
            if (1 == selectOne.getRecommend()) {
                return new Result(100, "商户已经被推荐", false);
            }
            selectOne.setRecommend(1);
            chamberStoreMapper.updateByPrimaryKeySelective(selectOne);
        }
        shoppingStore.setStoreRecommend(true);
        shoppingStoreMapper.updateByPrimaryKeySelective(shoppingStore);
        return new Result(200, "推荐成功", true);
    }

    @Override
    public Result cancelRecommend(Integer chamberId, String storeId) {
        ShoppingStore shoppingStore = shoppingStoreMapper.selectByPrimaryKey(storeId);
        if (Objects.isNull(shoppingStore)) {
            return new Result(100, "商户为空", false);
        }
        if (!shoppingStore.getStoreRecommend()) {
            return new Result(100, "商户还未被推荐", false);
        }
        ChamberStore chamberStore = new ChamberStore();
        chamberStore.setChamberId(chamberId);
        chamberStore.setStoreId(storeId);
        ChamberStore selectOne = chamberStoreMapper.selectOne(chamberStore);
        if (Objects.isNull(selectOne)) {
            return new Result(100, "商户还未被推荐", false);
        }
        if (0 == selectOne.getRecommend()) {
            return new Result(100, "商户已经被取消", false);
        }
        selectOne.setRecommend(0);
        chamberStoreMapper.updateByPrimaryKeySelective(selectOne);
        shoppingStore.setStoreRecommend(false);
        shoppingStoreMapper.updateByPrimaryKeySelective(shoppingStore);
        return new Result(200, "取消推荐成功", true);
    }

    @Override
    public List<ChamberStore> searchRecommend(Map param, Integer chamberId) {
        Example example = new Example(ChamberStore.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("chamberId", chamberId);
        List<ChamberStore> chamberStores = chamberStoreMapper.selectByExample(example);

        return chamberStores;
    }

    @Override
    public List<ShoppingStore> noRecommend() {
        Example example = new Example(ShoppingStore.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeRecommend", false);
        return shoppingStoreMapper.selectByExample(example);

    }

    @Override
    public ShoppingStore findByShoppingStoreById(String storeId) {
        return shoppingStoreMapper.selectByPrimaryKey(storeId);
    }


}
