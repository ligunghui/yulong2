package com.jidu.service.impl;

import com.jidu.mapper.BusinessAdminMapper;
import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.service.BusinessAdminService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/7 0007 上午 10:50
 * @Version:
 * @Description:
 */
@Service
public class BusinessAdminImpl implements BusinessAdminService {
    @Autowired
    private BusinessAdminMapper chamberAdminMapper;

    @Override
    public void updateBusinessAdmin(BusinessAdmin chamberAdmin) {
        String password = chamberAdmin.getPassword();
        String username = chamberAdmin.getUsername();
        password = new Md5Hash(password, username, 3).toString();  //1.密码，盐，加密次数
        chamberAdmin.setPassword(password);
        chamberAdminMapper.updateByPrimaryKeySelective(chamberAdmin);
    }

    @Override
    public void addBusinessAdmin(BusinessAdmin chamberAdmin) {
        String password = chamberAdmin.getPassword();
        String username = chamberAdmin.getUsername();
        password = new Md5Hash(password, username, 3).toString();  //1.密码，盐，加密次数
        chamberAdmin.setPassword(password);
        //chamberAdmin.setType(3);
        //chamberAdmin.setUseable(1);
        chamberAdminMapper.insert(chamberAdmin);
    }

    @Override
    public List<BusinessAdmin> findBusinessAdmin(String storeId) {
        Example example = new Example(BusinessAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        return chamberAdminMapper.selectByExample(example);
    }

    @Override
    public BusinessAdmin findBusinessAdminById(int id) {
        return chamberAdminMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteBusinessAdminById(int id) {
        chamberAdminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<BusinessAdmin> findBusinessAdminByUserName(String username) {
        Example example = new Example(BusinessAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        return chamberAdminMapper.selectByExample(example);
    }
}
