package com.jidu.mapper;

import com.jidu.pojo.sys.UserInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserInfoMapper extends Mapper<UserInfo> {
}
