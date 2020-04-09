package com.jidu.service;

import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.pojo.sys.Permission;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/7 0007 上午 10:50
 * @Version:
 * @Description:
 */
public interface BusinessAdminService {
    void updateBusinessAdmin(BusinessAdmin chamberAdmin);

    void addBusinessAdmin(BusinessAdmin chamberAdmin);

    List<BusinessAdmin> findBusinessAdmin(String storeId);

    BusinessAdmin findBusinessAdminById(int id);

    void deleteBusinessAdminById(int id);

    List<BusinessAdmin> findBusinessAdminByUserName(String username);

    BusinessAdmin findByUserName(String mobile);

    List<Permission> findPermission(BusinessAdmin businessAdmin);
}
