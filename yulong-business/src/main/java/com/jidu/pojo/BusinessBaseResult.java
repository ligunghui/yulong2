package com.jidu.pojo;

import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.pojo.sys.Permission;
import lombok.Getter;
import lombok.Setter;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;
import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-26 11:28
 */
@Setter
@Getter
public class BusinessBaseResult implements Serializable, AuthCachePrincipal {
    private  String username;
    private  String storeId;

    public  BusinessBaseResult (BusinessAdmin businessAdmin, List<Permission> list){
        this.username = businessAdmin.getUsername();
        this.storeId = businessAdmin.getStoreId();

    }
    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
