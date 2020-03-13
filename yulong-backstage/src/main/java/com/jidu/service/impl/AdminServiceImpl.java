package com.jidu.service.impl;

import com.jidu.entity.PageResult;
import com.jidu.mapper.BusinessAdminMapper;
import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.service.AdminService;
import net.bytebuddy.asm.Advice;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/13 0013 下午 4:26
 * @Version:
 * @Description:
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private BusinessAdminMapper businessAdminMapper;
    @Override
    public void updateBusinessAdmin(BusinessAdmin businessAdmin) {
        String password = businessAdmin.getPassword();
        String username = businessAdmin.getUsername();
        password = new Md5Hash(password, username, 3).toString();  //1.密码，盐，加密次数
        businessAdmin.setPassword(password);
        businessAdminMapper.updateByPrimaryKeySelective(businessAdmin);
    }

    @Override
    public void addBusinessAdmin(BusinessAdmin businessAdmin) {
        String password = businessAdmin.getPassword();
        String username = businessAdmin.getUsername();

        password = new Md5Hash(password, username, 3).toString();  //1.密码，盐，加密次数
        businessAdmin.setPassword(password);
        businessAdmin.setType(3);
        businessAdmin.setUseable(1);
        businessAdminMapper.insert(businessAdmin);
    }

    @Override
    public List<BusinessAdmin> findBusinessAdmin(String storeId) {
        Example example = new Example(BusinessAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        return businessAdminMapper.selectByExample(example);
    }

    @Override
    public BusinessAdmin findBusinessAdminById(int id) {
        return businessAdminMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteBusinessAdminById(int id) {
        businessAdminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<BusinessAdmin> findBusinessAdminByUserName(String username) {
        Example example = new Example(BusinessAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        return businessAdminMapper.selectByExample(example);
    }
}
