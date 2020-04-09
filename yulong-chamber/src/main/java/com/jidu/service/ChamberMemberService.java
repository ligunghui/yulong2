package com.jidu.service;

import com.jidu.pojo.shop.ChamberMember;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/4/2 0002 上午 9:22
 * @Version:
 * @Description:
 */
public interface ChamberMemberService {
    void save(ChamberMember chamberMember);

    void update(ChamberMember chamberMember);

    ChamberMember findById(Integer id);

    void delete(Integer id);

    List<ChamberMember> search(String storeId);
}
