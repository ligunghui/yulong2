package com.jidu.service.impl;

import com.jidu.mapper.ShoppingNoticeMapper;
import com.jidu.pojo.address.ShoppingAddress;
import com.jidu.pojo.notice.ShoppingNotice;
import com.jidu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-17 15:32
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private ShoppingNoticeMapper shoppingNoticeMapper;


    @Override
    public List<ShoppingNotice> findNoticeByStoreId(String storeId) {
        Example example=new Example(ShoppingNotice.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId",storeId);
        return shoppingNoticeMapper.selectByExample(example);
    }
}
