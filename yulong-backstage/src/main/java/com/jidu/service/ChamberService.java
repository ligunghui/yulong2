package com.jidu.service;

import com.jidu.entity.Result;
import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.pojo.shop.ShoppingChamber;

import java.util.List;
import java.util.Map;

public interface ChamberService {
    void save(ShoppingChamber shoppingChamber);

    void update(ShoppingChamber shoppingChamber);

    ShoppingChamber findById(Integer id);

    void delete(Integer id);

    List<ShoppingChamber> search(Map param, Integer status);

     List<BusinessAdmin> findBusinessAdminByUserName(String username);
    void  send(String mobile,String content);

    boolean checkTelephone(String telephone);
}
