package com.jidu.service;

import com.jidu.pojo.notice.ShoppingNotice;

import java.util.List;

public interface NoticeService {
    List<ShoppingNotice> findNoticeByStoreId(String storeId);
}
