package com.jidu.service.impl;

import com.jidu.mapper.ChamberMapper;
import com.jidu.pojo.shop.ShoppingChamber;
import com.jidu.service.ChamberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/4/20 0020 下午 3:26
 * @Version:
 * @Description:
 */
@Service
public class ChamberServiceImpl implements ChamberService {
    @Autowired
    private ChamberMapper chamberMapper;
    @Override
    public List<ShoppingChamber> search() {
        return chamberMapper.selectAll();
    }
}
