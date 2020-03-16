package com.jidu.service.impl;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.mapper.BusinessAdminMapper;
import com.jidu.mapper.ChamberMapper;
import com.jidu.mapper.NoticeMapper;
import com.jidu.pojo.notice.ShoppingNotice;
import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.pojo.shop.ShoppingChamber;
import com.jidu.service.ChamberService;
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
 * @create: 2020-02-22 09:01
 */
@Service
public class ChamberServiceImpl implements ChamberService {
    @Autowired
    private ChamberMapper chamberMapper;
    @Autowired
    private BusinessAdminMapper businessAdminMapper;
    @Autowired
    private NoticeMapper noticeMapper;


    @Override
    public void save(ShoppingChamber shoppingChamber) {
        chamberMapper.insert(shoppingChamber);
    }

    @Override
    public void update(ShoppingChamber shoppingChamber) {
        chamberMapper.updateByPrimaryKeySelective(shoppingChamber);
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
        if (0 != status) {
            criteria.andEqualTo("status", status);
        }
        return chamberMapper.selectByExample(example);
    }

    @Override
    public Result verify(Integer id, String violationReseaon, Integer status) {
        ShoppingChamber shoppingChamber = chamberMapper.selectByPrimaryKey(id);
        String username = shoppingChamber.getTelephone();
        if (username==null){
            return new Result(201, "没有填写手机号,审核失败", false);
        }
        if (shoppingChamber.getStatus()!=null){

            if (2 == status && 2 == shoppingChamber.getStatus()) {
                return new Result(201, "重复审核", false);
            }
        }
        shoppingChamber.setStatus(status);
        if (3 == status) {
            if (StringUtils.isNotEmpty(violationReseaon)) {
                shoppingChamber.setViolationReseaon(violationReseaon);
            }
            chamberMapper.updateByPrimaryKeySelective(shoppingChamber);
            return new Result(ResultCode.SUCCESS);
        }
        //第一次审核通过初始化商会管理员
        //

        List<BusinessAdmin> businessAdminByUserName = findBusinessAdminByUserName(username);
        if (!businessAdminByUserName.isEmpty()) {
            return new Result(201, "用户名重复", false);
        }
        //判断
        String password = "zhyl@123";
        password = new Md5Hash(password, username, 3).toString();  //1.密码，盐，加密次数
        BusinessAdmin businessAdmin = new BusinessAdmin();
        businessAdmin.setStoreId(id + "");
        businessAdmin.setUsername(username);
        businessAdmin.setPassword(password);
        businessAdmin.setType(2);
        businessAdmin.setUseable(1);
        businessAdminMapper.insert(businessAdmin);
        chamberMapper.updateByPrimaryKeySelective(shoppingChamber);
        //产生系统消息通知app端
        ShoppingNotice notice=new ShoppingNotice();
        notice.setCreateName("平台管理员");
        notice.setCreateId("0");
        notice.setType(1);
        notice.setTitle("商会申请通过");
        notice.setAddtime(new Date());
        notice.setContent("您申请的商会已经通过了,网址:http://zhyl.zh0476.com:9002,用户名是"+username+",初始密码是:"+"zhyl@123");
        noticeMapper.insert(notice);
        return new Result(ResultCode.SUCCESS);
    }

    public List<BusinessAdmin> findBusinessAdminByUserName(String username) {
        Example example = new Example(BusinessAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        return businessAdminMapper.selectByExample(example);
    }

}
