package com.jidu.service.impl;

import com.jidu.mapper.ElectronicCardMapper;
import com.jidu.pojo.card.ElectronicCard;
import com.jidu.service.ElectronicCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-12 10:46
 */
@Service
public class ElectronicCardServiceImpl implements ElectronicCardService {
    @Autowired
    private ElectronicCardMapper electronicCardMapper;
    @Override
    public void addElectronicCard(ElectronicCard electronicCard) {
        electronicCardMapper.insert(electronicCard);
    }

    @Override
    public void editElectronicCard(ElectronicCard electronicCard) {
        electronicCardMapper.updateByPrimaryKey(electronicCard);
    }

    @Override
    public ElectronicCard findElectronicCard(String userId) {

        return electronicCardMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void deleteElectronicCard(String userId) {
        electronicCardMapper.deleteByPrimaryKey(userId);
    }
}
