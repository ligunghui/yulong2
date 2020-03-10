package com.jidu.mapper;

import com.jidu.pojo.notice.ShoppingNotice;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: liguanghui
 * Date: 2020/3/7 0007 上午 11:00
 * @Version:
 * @Description:
 */
@Repository
public interface NoticeMapper extends Mapper<ShoppingNotice> {
}
