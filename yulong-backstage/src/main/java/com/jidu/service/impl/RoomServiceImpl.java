package com.jidu.service.impl;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.mapper.RoomChatMapper;
import com.jidu.pojo.room.RoomChat;
import com.jidu.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/16 0016 上午 11:16
 * @Version:
 * @Description:
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomChatMapper roomChatMapper;

    @Override
    public void delete(Integer id) {
        roomChatMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Result verify(Integer id, String violationReseaon, int storeStatus) {
        RoomChat roomChat = roomChatMapper.selectByPrimaryKey(id);
        roomChat.setStatus(storeStatus);
        roomChatMapper.updateByPrimaryKeySelective(roomChat);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public List<RoomChat> search(int storeStatus) {
        Example example = new Example(RoomChat.class);
        Example.Criteria criteria = example.createCriteria();
        if (storeStatus != 0) {
            criteria.andEqualTo("status", storeStatus);
        }
        return roomChatMapper.selectByExample(example);
    }
}
