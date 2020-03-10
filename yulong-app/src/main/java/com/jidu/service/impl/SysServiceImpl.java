package com.jidu.service.impl;

import com.jidu.mapper.AboutUsMapper;
import com.jidu.mapper.AppUpdateMapper;
import com.jidu.mapper.ShoppingNoticeMapper;
import com.jidu.pojo.address.ShoppingAddress;
import com.jidu.pojo.notice.ShoppingNotice;
import com.jidu.pojo.sys.AboutUs;
import com.jidu.pojo.sys.AppUpdate;
import com.jidu.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/6 0006 下午 3:28
 * @Version:
 * @Description:
 */
@Service
public class SysServiceImpl implements SysService {
    @Autowired
    private AboutUsMapper aboutUsMapper;
    @Autowired
    private ShoppingNoticeMapper shoppingNoticeMapper;
    @Autowired
    private AppUpdateMapper appUpdateMapper;

    @Override
    public AboutUs findAboutAs() {
        return aboutUsMapper.selectAll().get(0);
    }

    @Override
    public List<ShoppingNotice> findNotice() {
        Example example = new Example(ShoppingNotice.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", 1);
        return shoppingNoticeMapper.selectByExample(example);
    }

    @Override
    public List<AppUpdate> findAppUpdate() {
        Example example = new Example(AppUpdate.class);
        example.setOrderByClause("updateTime DESC");
        return appUpdateMapper.selectByExample(example);
    }

    @Override
    public AppUpdate findAppUpdateById(int id) {
        return appUpdateMapper.selectByPrimaryKey(id);
    }
}
