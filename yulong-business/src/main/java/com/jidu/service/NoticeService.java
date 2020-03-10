package com.jidu.service;

import com.jidu.pojo.notice.ShoppingNotice;

import java.util.List;

public interface NoticeService {
    void save(ShoppingNotice shoppingNotice);

    void update(ShoppingNotice shoppingNotice);

    ShoppingNotice findById(Integer id);

    void delete(Integer id);

    List<ShoppingNotice> search(String storeId, Integer type);
}
