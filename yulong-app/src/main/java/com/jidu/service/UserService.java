package com.jidu.service;

import com.jidu.pojo.sys.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-11 14:40
 */
public interface UserService {
    UserInfo findByMobile(String mobile);

    void save(UserInfo userInfo);

    void update(UserInfo userInfo);

    UserInfo findById(String id);

    List<UserInfo> search(Map param);

    String sendCode(String mobile);

    UserInfo getByWxOpenID(String openID, String wxName, HttpServletRequest request);

    UserInfo findByInvitationCode(String invitationCode);
}
