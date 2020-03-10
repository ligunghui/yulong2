package com.jidu.mapper;

import com.jidu.pojo.sys.UserInfo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: liguanghui
 * Date: 2020/3/6 0006 下午 5:35
 * @Version:
 * @Description:
 */
@Repository
public interface UserInfoMapper extends Mapper<UserInfo> {
}
