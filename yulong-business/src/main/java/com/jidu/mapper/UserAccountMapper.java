package com.jidu.mapper;

import com.jidu.pojo.sys.UserAccount;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: liguanghui
 * Date: 2020/3/25 0025 上午 11:17
 * @Version:
 * @Description:
 */
@Repository
public interface UserAccountMapper extends Mapper<UserAccount> {
}
