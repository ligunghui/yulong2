package com.jidu.service.impl;

import com.jidu.mapper.LocalServiceStoreMapper;
import com.jidu.pojo.local.LocalServiceStore;
import com.jidu.service.LocalServiceStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/13 0013 下午 8:11
 * @Version:
 * @Description:
 */
@Service
public class LocalServiceStoreServiceImpl implements LocalServiceStoreService {
    @Autowired
    private LocalServiceStoreMapper localServiceStoreMapper;
    @Override
    public List<LocalServiceStore> search() {
        return localServiceStoreMapper.selectAll();
    }

    @Override
    public LocalServiceStore findById(Integer id) {
        return localServiceStoreMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(LocalServiceStore localServiceStore) {
        localServiceStoreMapper.insert(localServiceStore);
    }

    @Override
    public void update(LocalServiceStore localServiceStore) {
        localServiceStoreMapper.updateByPrimaryKeySelective(localServiceStore);
    }

    @Override
    public void delete(Integer id) {
        localServiceStoreMapper.deleteByPrimaryKey(id);
    }
}
