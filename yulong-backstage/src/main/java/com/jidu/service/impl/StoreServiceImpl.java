package com.jidu.service.impl;

import com.jidu.mapper.BusinessAdminMapper;
import com.jidu.mapper.ShoppingStoreMapper;
import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.pojo.shop.ShoppingStore;
import com.jidu.service.StoreService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-26 16:05
 */

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private ShoppingStoreMapper shoppingStoreMapper;
    @Autowired
    private BusinessAdminMapper businessAdminMapper;

    @Override
    public List<ShoppingStore> search(Map param, int storeStatus) {
        Example example = new Example(ShoppingStore.class);
        Example.Criteria criteria = example.createCriteria();
        if (0 != storeStatus) {

            criteria.andEqualTo("storeStatus", storeStatus);
        }
        return shoppingStoreMapper.selectByExample(example);
    }

    @Override
    public ShoppingStore findShoppingStoreById(String id) {
        return shoppingStoreMapper.selectByPrimaryKey(id);
    }

    @Override
    public void verify(String id, String violationReseaon, int storeStatus) {
        ShoppingStore shoppingStore = shoppingStoreMapper.selectByPrimaryKey(id);
        if (2 == storeStatus && 2 == shoppingStore.getStoreStatus()) {
            return;
        }
        shoppingStore.setStoreStatus(storeStatus);
        if (3 == storeStatus) {
            if (StringUtils.isNotEmpty(violationReseaon)) {
                shoppingStore.setViolationReseaon(violationReseaon);
            }
            shoppingStoreMapper.updateByPrimaryKeySelective(shoppingStore);
            return;
        }
        //第一次审核通过初始化商户管理员
        String username = shoppingStore.getStoreTelephone();
        String password = "zhyl@123";
        password = new Md5Hash(password, username, 3).toString();  //1.密码，盐，加密次数
        BusinessAdmin businessAdmin = new BusinessAdmin();
        businessAdmin.setStoreId(id);
        businessAdmin.setUsername(username);
        businessAdmin.setPassword(password);
        businessAdmin.setType(3);
        businessAdmin.setUseable(1);
        businessAdminMapper.insert(businessAdmin);
        shoppingStoreMapper.updateByPrimaryKeySelective(shoppingStore);
    }
}
