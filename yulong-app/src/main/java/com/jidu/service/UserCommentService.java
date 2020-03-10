package com.jidu.service;

import com.jidu.pojo.comment.UserComment;

/**
 * @Author: liguanghui
 * Date: 2020/3/9 0009 下午 3:38
 * @Version:
 * @Description:
 */
public interface UserCommentService {
    void addComment(UserComment userComment);

    void thumbs(Integer id);

    void delete(Integer id);

    UserComment findById(Integer id);
}
