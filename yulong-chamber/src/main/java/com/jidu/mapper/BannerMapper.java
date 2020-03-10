package com.jidu.mapper;

import com.jidu.pojo.shop.ShoppingBanner;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: liguanghui
 * Date: 2020/3/7 0007 下午 3:21
 * @Version:
 * @Description:
 */
@Repository
public interface BannerMapper extends Mapper<ShoppingBanner> {
}
