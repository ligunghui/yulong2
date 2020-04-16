package com.jidu.shiro.result;

import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.pojo.sys.Permission;


import com.jidu.pojo.sys.UserInfo;
import lombok.Getter;
import lombok.Setter;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;
import java.util.*;

@Setter
@Getter
public class ProfileResult implements Serializable, AuthCachePrincipal {
    private String userId;
    private String mobile;
    private String userPhoto;
    private String username;
    private String storeId;
    private Set<String> perms = new HashSet<>();
    private Set<String> permsName = new HashSet<>();

    /**
     * @param user
     */
    public ProfileResult(UserInfo user, List<Permission> list) {
        this.mobile = user.getMobile();
        this.username = user.getUsername();
        this.userId = user.getId();
        this.userPhoto = user.getUserphoto();
        Set<String> perms = new HashSet<>();
        Set<String> permsName = new HashSet<>();
        for (Permission permission : list) {
            perms.add(permission.getDescription());
            permsName.add(permission.getName());
        }
        this.perms = perms;
        this.permsName = permsName;

    }

    public ProfileResult(BusinessAdmin businessAdmin, List<Permission> list) {
        this.username = businessAdmin.getUsername();
        this.storeId = businessAdmin.getStoreId();
        Set<String> permsName = new HashSet<>();
        Set<String> perms = new HashSet<>();
        for (Permission permission : list) {
            perms.add(permission.getDescription());
            permsName.add(permission.getName());
        }
        this.perms = perms;
        this.permsName = permsName;
    }


    public ProfileResult(UserInfo user) {
        this.mobile = user.getMobile();
        this.username = user.getUsername();
        this.userId = user.getId();
        //Set<Role> roles = user.getRoles();
        Set<String> menus = new HashSet<>();
        Set<String> points = new HashSet<>();
        Set<String> apis = new HashSet<>();
       /* for (Role role : roles) {
            Set<Permission> perms = role.getPermissions();
            for (Permission perm : perms) {
                String code = perm.getCode();
                if(perm.getType() == 1) {
                    menus.add(code);
                }else if(perm.getType() == 2) {
                    points.add(code);
                }else {
                    apis.add(code);
                }
            }
        }

        this.roles.put("menus",menus);
        this.roles.put("points",points);
        this.roles.put("apis",apis);*/
    }

    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
