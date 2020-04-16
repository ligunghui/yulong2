package com.jidu.service.impl;

import com.jidu.mapper.LocalServiceOrderMapper;
import com.jidu.pojo.goods.GoodsType;
import com.jidu.pojo.local.LocalServiceOrder;
import com.jidu.service.LocalServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/25 0025 下午 7:09
 * @Version:
 * @Description:
 */
@Service
public class LocalServiceOrderServiceImpl implements LocalServiceOrderService {
    @Autowired
    private LocalServiceOrderMapper localServiceOrderMapper;
    @Override
    public void update(LocalServiceOrder localServiceOrder) {
        localServiceOrderMapper.updateByPrimaryKeySelective(localServiceOrder);
    }

    @Override
    public LocalServiceOrder findById(Integer id) {
        return localServiceOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
        localServiceOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<LocalServiceOrder> search(String storeId) {
        Example example1 = new Example(LocalServiceOrder.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("storeId", storeId);
        return localServiceOrderMapper.selectByExample(example1);
    }
}
