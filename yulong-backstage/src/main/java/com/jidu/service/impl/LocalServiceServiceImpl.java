package com.jidu.service.impl;

import com.jidu.mapper.LocalServiceMapper;
import com.jidu.mapper.LocalServiceStoreMapper;
import com.jidu.pojo.activity.ShoppingActivity;
import com.jidu.pojo.local.LocalService;
import com.jidu.pojo.local.LocalServiceStore;
import com.jidu.service.LocalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/13 0013 下午 7:39
 * @Version:
 * @Description:
 */
@Service
public class LocalServiceServiceImpl implements LocalServiceService {
    @Autowired
    private LocalServiceMapper localServiceMapper;
    @Autowired
    private LocalServiceStoreMapper localServiceStoreMapper;
    @Override
    public void update(LocalService localService) {
        localServiceMapper.updateByPrimaryKeySelective(localService);
    }

    @Override
    public void add(LocalService localService) {
        localServiceMapper.insert(localService);
    }

    @Override
    public LocalService findById(Integer id) {
        return localServiceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<LocalService> search() {
        return localServiceMapper.selectAll();
    }

    @Override
    public List<LocalServiceStore> findByServiceId(Integer serviceId) {
        Example example = new Example(LocalServiceStore.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("serviceId", serviceId);
        return localServiceStoreMapper.selectByExample(example);
    }
}
