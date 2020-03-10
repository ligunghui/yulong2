package com.jidu.service;

import com.jidu.pojo.shop.ChamberUser;

import java.util.List;

public interface ChamberUserService {
    void apply(ChamberUser chamberUser);

    void cancelChamberByUserId(Integer id);

    List<ChamberUser> findChamberUserByChamberId(Integer id);

    void delChamberUser(Integer id, Integer status, String violationReseaon, String userName);

    ChamberUser findChamberUserById(Integer id);
}
