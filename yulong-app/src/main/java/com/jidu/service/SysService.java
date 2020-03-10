package com.jidu.service;

import com.jidu.pojo.notice.ShoppingNotice;
import com.jidu.pojo.sys.AboutUs;
import com.jidu.pojo.sys.AppUpdate;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/6 0006 下午 3:27
 * @Version:
 * @Description:
 */
public interface SysService {
   AboutUs findAboutAs();

    List<ShoppingNotice> findNotice();

    List<AppUpdate> findAppUpdate();

    AppUpdate findAppUpdateById(int id);
}
