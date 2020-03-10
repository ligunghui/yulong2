package com.jidu.shiro;

import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.pojo.sys.Permission;
import com.jidu.service.BusinessAdminService;
import com.jidu.shiro.realm.MyRealm;
import com.jidu.shiro.result.ProfileResult;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BusinessRealm extends MyRealm {
    @Autowired
    private BusinessAdminService businessAdminService;
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String mobile = upToken.getUsername();
        String password = new String(upToken.getPassword());

        BusinessAdmin businessAdmin = businessAdminService.findByUserName(mobile);
        if (businessAdmin != null && businessAdmin.getPassword().equals(password)) {
            //.构造安全数据并返回（安全数据：用户基本数据，权限信息 profileResult）
           List<Permission> list = businessAdminService.findPermission(businessAdmin);
            //list.add(new Permission());
            ProfileResult result = new ProfileResult(businessAdmin,list);
            //构造方法：安全数据，密码，realm域名
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(result, businessAdmin.getPassword(), this.getName());
            return info;
        }

        return null;
    }
}
