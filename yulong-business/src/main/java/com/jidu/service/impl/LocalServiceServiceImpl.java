package com.jidu.service.impl;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.mapper.LocalServiceMapper;
import com.jidu.mapper.LocalServiceStoreMapper;
import com.jidu.pojo.Tree;
import com.jidu.pojo.TreeGroup;
import com.jidu.pojo.goods.ChannelType;
import com.jidu.pojo.goods.GoodsType;
import com.jidu.pojo.local.LocalService;
import com.jidu.pojo.local.LocalServiceStore;
import com.jidu.service.LocalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/25 0025 下午 2:25
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
    public Result<Tree> findAll(String storeId) {
        List<Tree> trees = new ArrayList<>();
        List<LocalService> localServices = localServiceMapper.selectAll();
        for (LocalService localService : localServices) {
            Integer id = localService.getId();
            boolean b = checked(storeId, id);
            Tree tree = new Tree(localService.getName(), id, b);
            trees.add(tree);
        }
        return new Result(ResultCode.SUCCESS, trees);
    }

    @Override
    public void add(String storeId, Integer[] typeIds) {
        Example example1 = new Example(LocalServiceStore.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("storeId", storeId);
        localServiceStoreMapper.deleteByExample(example1);
        for (Integer typeId : typeIds) {
            LocalServiceStore localServiceStore = new LocalServiceStore();
            localServiceStore.setServiceId(typeId);
            localServiceStore.setStoreId(storeId);
            localServiceStoreMapper.insert(localServiceStore);

        }
    }

    private boolean checked(String storeId, Integer id) {
        Example example1 = new Example(LocalServiceStore.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("serviceId", id);
        criteria1.andEqualTo("storeId", storeId);
        List<LocalServiceStore> list = localServiceStoreMapper.selectByExample(example1);
        return !list.isEmpty();
    }
}
