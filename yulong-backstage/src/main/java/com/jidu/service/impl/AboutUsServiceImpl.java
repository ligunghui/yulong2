package com.jidu.service.impl;

import com.jidu.mapper.AboutUsMapper;
import com.jidu.pojo.sys.AboutUs;
import com.jidu.service.AboutUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/6 0006 下午 3:02
 * @Version:
 * @Description:
 */
@Service
public class AboutUsServiceImpl implements AboutUsService {
    @Autowired
    private AboutUsMapper aboutUsMapper;

    @Override
    public void save(AboutUs aboutUs) {
        aboutUsMapper.insert(aboutUs);
    }

    @Override
    public void update(AboutUs aboutUs) {
        aboutUsMapper.updateByPrimaryKey(aboutUs);
    }

    @Override
    public AboutUs findById(Integer id) {
        return aboutUsMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
        aboutUsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<AboutUs> search() {

        return aboutUsMapper.selectAll();
    }
}
