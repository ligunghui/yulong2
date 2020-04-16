package com.jidu.controller;

import com.jidu.shiro.result.ProfileResult;
import net.bytebuddy.asm.Advice;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-26 11:26
 */
public class BusinessBaseController {
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    protected String storeId;
    protected String userName;
    protected Set<String> perms = new HashSet<>();
    protected Set<String> permsName = new HashSet<>();


    //使用shiro获取
    @ModelAttribute
    public void setResAnReq(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;

        //获取session中的安全数据
        Subject subject = SecurityUtils.getSubject();
        //1.subject获取所有的安全数据集合
        PrincipalCollection principals = subject.getPrincipals();
        if (principals != null && !principals.isEmpty()) {
            //2.获取安全数据
            ProfileResult result = (ProfileResult) principals.getPrimaryPrincipal();
            this.storeId = result.getStoreId();
            this.userName = result.getUsername();
            this.perms = result.getPerms();
            this.permsName = result.getPermsName();

        }
    }

}
