package com.jidu.service.impl;

import com.jidu.mapper.BusinessAdminMapper;
import com.jidu.mapper.StoreMapper;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.pojo.shop.ShoppingStore;
import com.jidu.service.StoreService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-06 19:18
 */
@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private BusinessAdminMapper businessAdminMapper;


    @Override
    public void update(ShoppingStore shoppingStore) {
        storeMapper.updateByPrimaryKey(shoppingStore);
    }

    @Override
    public ShoppingStore findById(String id) {
        return storeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(String id) {
        storeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateBusinessAdmin(BusinessAdmin businessAdmin) {
        String password = businessAdmin.getPassword();
        String username = businessAdmin.getUsername();
        password = new Md5Hash(password, username, 3).toString();  //1.密码，盐，加密次数
        businessAdmin.setPassword(password);
        businessAdminMapper.updateByPrimaryKey(businessAdmin);
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


}
