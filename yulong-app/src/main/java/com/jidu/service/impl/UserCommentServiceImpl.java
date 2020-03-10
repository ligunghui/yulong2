package com.jidu.service.impl;

import com.jidu.mapper.UserCommentMapper;
import com.jidu.pojo.comment.UserComment;
import com.jidu.service.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: liguanghui
 * Date: 2020/3/9 0009 下午 3:39
 * @Version:
 * @Description:
 */
@Service
public class UserCommentServiceImpl implements UserCommentService {
    @Autowired
    private UserCommentMapper userCommentMapper;

    @Override
    public void addComment(UserComment userComment) {
        userComment.setCommentTime(new Date());
        userComment.setThumbsNum(0);
        userCommentMapper.insert(userComment);
    }

    @Override
    public void thumbs(Integer id) {
        UserComment userComment = userCommentMapper.selectByPrimaryKey(id);
        userComment.setThumbsNum(userComment.getThumbsNum() + 1);
        userCommentMapper.updateByPrimaryKey(userComment);
    }

    @Override
    public void delete(Integer id) {
        userCommentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UserComment findById(Integer id) {
        return userCommentMapper.selectByPrimaryKey(id);
    }
}
