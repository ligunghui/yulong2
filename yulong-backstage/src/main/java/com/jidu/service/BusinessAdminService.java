package com.jidu.service;

import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.pojo.sys.Permission;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/12 0012 下午 5:04
 * @Version:
 * @Description:
 */
public interface BusinessAdminService {
    BusinessAdmin findByUserName(String mobile);

    List<Permission> findPermission(BusinessAdmin businessAdmin);
}
