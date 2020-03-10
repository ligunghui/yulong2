package com.jidu.mapper;

import com.jidu.pojo.notice.ShoppingNotice;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-10 16:39
 */
@Repository
public interface NoticeMapper extends Mapper<ShoppingNotice> {
}
