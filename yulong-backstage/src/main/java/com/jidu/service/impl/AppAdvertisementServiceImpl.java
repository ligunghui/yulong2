package com.jidu.service.impl;

import com.jidu.mapper.AppAdvertisementMapper;
import com.jidu.pojo.sys.AppAdvertisement;
import com.jidu.service.AppAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/10 0010 下午 6:20
 * @Version:
 * @Description:
 */
@Service
public class AppAdvertisementServiceImpl implements AppAdvertisementService {
    @Autowired
    private AppAdvertisementMapper appAdvertisementMapper;
    @Override
    public List<AppAdvertisement> search() {
        return appAdvertisementMapper.selectAll();
    }

    @Override
    public void delete(Integer id) {
        appAdvertisementMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void add(AppAdvertisement appAdvertisement) {
        String img = appAdvertisement.getImg();
        appAdvertisement.setType(1);
        if (img.endsWith(".avi")||img.endsWith(".mp4")||img.endsWith(".wmv")){
            appAdvertisement.setType(2);
        }

        appAdvertisementMapper.insert(appAdvertisement);
    }
}
