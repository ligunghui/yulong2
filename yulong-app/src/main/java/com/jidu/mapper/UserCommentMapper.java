package com.jidu.mapper;

import com.jidu.pojo.comment.UserComment;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: liguanghui
 * Date: 2020/3/9 0009 下午 3:42
 * @Version:
 * @Description:
 */
@Repository
public interface UserCommentMapper extends Mapper<UserComment> {
}
