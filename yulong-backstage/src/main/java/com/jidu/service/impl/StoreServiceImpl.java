package com.jidu.service.impl;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.mapper.BusinessAdminMapper;
import com.jidu.mapper.NoticeMapper;
import com.jidu.mapper.ShoppingStoreMapper;
import com.jidu.pojo.notice.ShoppingNotice;
import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.pojo.shop.ShoppingStore;
import com.jidu.service.StoreService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
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
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<ShoppingStore> search(Map param, int storeStatus, Integer storeType) {
        Example example = new Example(ShoppingStore.class);
        Example.Criteria criteria = example.createCriteria();
        if (0 != storeStatus) {

            criteria.andEqualTo("storeStatus", storeStatus);
        }
        criteria.andEqualTo("storeType",storeType);
        return shoppingStoreMapper.selectByExample(example);
    }

    @Override
    public ShoppingStore findShoppingStoreById(String id) {
        return shoppingStoreMapper.selectByPrimaryKey(id);
    }

    @Override
    public Result verify(String id, String violationReseaon, int storeStatus) {
        ShoppingStore shoppingStore = shoppingStoreMapper.selectByPrimaryKey(id);
        if (2 == storeStatus && 2 == shoppingStore.getStoreStatus()) {
            return new Result(201, "重复审核", false);
        }
        shoppingStore.setStoreStatus(storeStatus);
        if (3 == storeStatus) {
            if (StringUtils.isNotEmpty(violationReseaon)) {
                shoppingStore.setViolationReseaon(violationReseaon);
            }
            shoppingStoreMapper.updateByPrimaryKeySelective(shoppingStore);
            return new Result(ResultCode.SUCCESS);
        }
        //第一次审核通过初始化商户管理员
        String username = shoppingStore.getStoreTelephone();
        List<BusinessAdmin> businessAdminByUserName = findBusinessAdminByUserName(username);
        if (!businessAdminByUserName.isEmpty()) {
            return new Result(201, "用户名重复", false);
        }
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
        ShoppingNotice notice=new ShoppingNotice();
        notice.setCreateName("平台管理员");
        notice.setCreateId("0");
        notice.setType(1);
        notice.setAddtime(new Date());
        notice.setTitle("商户申请通过");
        notice.setContent("您申请的商户已经通过了,网址:http://zhyl.zh0476.com:9001,用户名是"+username+",初始密码是:"+"zhyl@123");
        noticeMapper.insert(notice);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public List<BusinessAdmin> findBusinessAdminByUserName(String username) {
        Example example = new Example(BusinessAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        return businessAdminMapper.selectByExample(example);
    }
}
