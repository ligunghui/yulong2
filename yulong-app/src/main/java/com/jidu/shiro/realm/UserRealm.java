package com.jidu.shiro.realm;

import com.jidu.pojo.sys.Permission;
import com.jidu.pojo.sys.UserInfo;
import com.jidu.service.UserService;
import com.jidu.shiro.result.ProfileResult;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserRealm extends MyRealm {
    @Autowired
    private UserService userService;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String mobile = upToken.getUsername();
        String password = new String(upToken.getPassword());

        UserInfo user = userService.findByMobile(mobile);
        if (user != null && user.getPassword().equals(password)) {
            //.构造安全数据并返回（安全数据：用户基本数据，权限信息 profileResult）
            List<Permission> list = new ArrayList<>();
           //list.add(new Permission());
            ProfileResult result = new ProfileResult(user,list);

            //构造方法：安全数据，密码，realm域名
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(result, user.getPassword(), this.getName());
          
            return info;
        }

        return null;
    }
}
