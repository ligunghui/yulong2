package com.jidu.service.impl;

import com.jidu.mapper.RoomChatMapper;
import com.jidu.pojo.activity.ShoppingActivity;
import com.jidu.pojo.room.RoomChat;
import com.jidu.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/16 0016 上午 10:20
 * @Version:
 * @Description:
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomChatMapper roomChatMapper;
    @Override
    public void save(RoomChat roomChat) {
        roomChatMapper.insert(roomChat);
    }

    @Override
    public void delete(Integer id) {
        roomChatMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(RoomChat roomChat) {
        roomChatMapper.updateByPrimaryKey(roomChat);
    }

    @Override
    public List<RoomChat> findByStoreId(String storeId) {
        Example example = new Example(RoomChat.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("chamberId", storeId);
        return roomChatMapper.selectByExample(example);
    }

    @Override
    public RoomChat findById(Integer id) {
        return roomChatMapper.selectByPrimaryKey(id);
    }
}
