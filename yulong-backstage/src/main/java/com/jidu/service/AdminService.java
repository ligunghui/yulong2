package com.jidu.service;

import com.jidu.pojo.shop.BusinessAdmin;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/13 0013 下午 4:26
 * @Version:
 * @Description:
 */
public interface AdminService {
    void deleteBusinessAdminById(int id);

    void updateBusinessAdmin(BusinessAdmin businessAdmin);

    List<BusinessAdmin> findBusinessAdminByUserName(String username);

    void addBusinessAdmin(BusinessAdmin businessAdmin);

    List<BusinessAdmin> findBusinessAdmin(String s);

    BusinessAdmin findBusinessAdminById(int id);
}
