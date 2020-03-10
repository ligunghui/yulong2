package com.jidu.service.impl;

import com.jidu.mapper.BusinessAdminMapper;
import com.jidu.mapper.ChamberMapper;
import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.pojo.shop.ShoppingChamber;
import com.jidu.service.ChamberService;
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
 * @create: 2020-02-22 09:01
 */
@Service
public class ChamberServiceImpl implements ChamberService {
    @Autowired
    private ChamberMapper chamberMapper;
    @Autowired
    private BusinessAdminMapper businessAdminMapper;


    @Override
    public void save(ShoppingChamber shoppingChamber) {
        chamberMapper.insert(shoppingChamber);
    }

    @Override
    public void update(ShoppingChamber shoppingChamber) {
        chamberMapper.updateByPrimaryKey(shoppingChamber);
    }

    @Override
    public ShoppingChamber findById(Integer id) {
        return chamberMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
        chamberMapper.deleteByPrimaryKey(id);

    }

    @Override
    public List<ShoppingChamber> search(Map param, Integer status) {
        Example example = new Example(ShoppingChamber.class);
        Example.Criteria criteria = example.createCriteria();
        if (0!=status){
            criteria.andEqualTo("status",status);
        }
        return chamberMapper.selectByExample(example);
    }

    @Override
    public void verify(Integer id, String violationReseaon, Integer status) {
        ShoppingChamber shoppingChamber = chamberMapper.selectByPrimaryKey(id);
        if (2 == status && 2 == shoppingChamber.getStatus()) {
            return;
        }
        shoppingChamber.setStatus(status);
        if (3 == status) {
            if (StringUtils.isNotEmpty(violationReseaon)) {
                shoppingChamber.setViolationReseaon(violationReseaon);
            }
            chamberMapper.updateByPrimaryKey(shoppingChamber);
            return;
        }
        //第一次审核通过初始化商会管理员
        String username = shoppingChamber.getName();
        String password = "zhyl@123";
        password = new Md5Hash(password, username, 3).toString();  //1.密码，盐，加密次数
        BusinessAdmin businessAdmin = new BusinessAdmin();
        businessAdmin.setStoreId(id+"");
        businessAdmin.setUsername(username);
        businessAdmin.setPassword(password);
        businessAdmin.setType(2);
        businessAdmin.setUseable(1);
        businessAdminMapper.insert(businessAdmin);
        chamberMapper.updateByPrimaryKey(shoppingChamber);
    }


}
