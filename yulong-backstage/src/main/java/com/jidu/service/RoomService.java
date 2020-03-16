package com.jidu.service;

import com.jidu.entity.Result;
import com.jidu.pojo.room.RoomChat;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/16 0016 上午 11:15
 * @Version:
 * @Description:
 */
public interface RoomService {
    void delete(Integer id);

    Result verify(Integer id, String violationReseaon, int storeStatus);

    List<RoomChat> search(int storeStatus);
}
