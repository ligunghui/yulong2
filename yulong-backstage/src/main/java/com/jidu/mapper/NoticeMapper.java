package com.jidu.mapper;

import com.jidu.pojo.notice.ShoppingNotice;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface NoticeMapper extends Mapper<ShoppingNotice> {
}
