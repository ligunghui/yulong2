package com.jidu.service.impl;

import com.jidu.mapper.NoticeMapper;
import com.jidu.pojo.notice.ShoppingNotice;
import com.jidu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-10 16:43
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public void save(ShoppingNotice shoppingNotice) {
        shoppingNotice.setAddtime(new Date());
        shoppingNotice.setStoreId("0");
        shoppingNotice.setCreateName("平台管理员");
        shoppingNotice.setCreateId("0");
        noticeMapper.insert(shoppingNotice);
    }

    @Override
    public void update(ShoppingNotice shoppingNotice) {
        noticeMapper.updateByPrimaryKeySelective(shoppingNotice);
    }

    @Override
    public ShoppingNotice findById(Integer id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
      noticeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<ShoppingNotice> search(String storeId, Integer type) {
        Example example=new Example(ShoppingNotice.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId",storeId);
        criteria.andEqualTo("type",type);
        return noticeMapper.selectByExample(example);
    }
}
