package com.jidu.service;

import com.jidu.pojo.sys.AboutUs;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/6 0006 下午 3:01
 * @Version:
 * @Description:
 */
public interface AboutUsService {
    void save(AboutUs aboutUs);

    void update(AboutUs aboutUs);

    AboutUs findById(Integer id);

    void delete(Integer id);

    List<AboutUs> search();
}
