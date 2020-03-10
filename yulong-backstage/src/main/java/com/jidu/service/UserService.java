package com.jidu.service;

import com.jidu.pojo.sys.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/3/6 0006 下午 5:27
 * @Version:
 * @Description:
 */
public interface UserService {
    List<UserInfo> search(Map param);
}
