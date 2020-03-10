package com.jidu.service;

import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.pojo.sys.Permission;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-26 10:47
 */
public interface BusinessAdminService {
    BusinessAdmin findByUserName(String mobile);

    List<Permission> findPermission(BusinessAdmin businessAdmin);
}
