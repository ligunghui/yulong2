package com.jidu.mapper;

import com.jidu.pojo.address.ShoppingAddress;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface AddressMapper extends Mapper<ShoppingAddress> {
}
