package com.jidu.service.impl;

import com.jidu.mapper.LocalServiceGoodsMapper;
import com.jidu.mapper.LocalServiceMapper;
import com.jidu.mapper.LocalServiceStoreMapper;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.local.LocalService;
import com.jidu.pojo.local.LocalServiceGoods;
import com.jidu.pojo.local.LocalServiceStore;
import com.jidu.service.LocalServiceGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/3/25 0025 下午 2:02
 * @Version:
 * @Description:
 */
@Service
public class LocalServiceGoodsServiceImpl implements LocalServiceGoodsService {
    @Autowired
    private LocalServiceGoodsMapper localServiceGoodsMapper;
    @Autowired
    private LocalServiceStoreMapper localServiceStoreMapper;
    @Autowired
    private LocalServiceMapper localServiceMapper;

    @Override
    public void save(LocalServiceGoods localServiceGoods) {
        localServiceGoodsMapper.insert(localServiceGoods);
    }

    @Override
    public void update(LocalServiceGoods localServiceGoods) {
        localServiceGoodsMapper.updateByPrimaryKeySelective(localServiceGoods);
    }

    @Override
    public LocalServiceGoods findById(Long id) {
        return localServiceGoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Long id) {
        localServiceGoodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<LocalServiceGoods> search(Map param, String storeId) {
        Example example = new Example(LocalServiceGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        return localServiceGoodsMapper.selectByExample(example);
    }

    @Override
    public List<LocalService> findService(String storeId) {
        List<LocalService> localServices = new ArrayList<>();
        Example example1 = new Example(LocalServiceStore.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("storeId", storeId);
        List<LocalServiceStore> list = localServiceStoreMapper.selectByExample(example1);
        for (LocalServiceStore localServiceStore : list) {
            LocalService localService = localServiceMapper.selectByPrimaryKey(localServiceStore.getServiceId());
            localServices.add(localService);

        }
        return localServices;
    }
}
