package com.jidu.service;

import com.jidu.pojo.room.RoomChat;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/16 0016 上午 10:19
 * @Version:
 * @Description:
 */
public interface RoomService {
    void save(RoomChat roomChat);

    void delete(Integer id);

    void update(RoomChat roomChat);

    List<RoomChat> findByStoreId(String storeId);

    RoomChat findById(Integer id);
}
