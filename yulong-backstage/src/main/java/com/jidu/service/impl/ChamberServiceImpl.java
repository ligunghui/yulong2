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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
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
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public void save(ShoppingChamber shoppingChamber) {
        shoppingChamber.setTotalMoney(new BigDecimal("0"));
        shoppingChamber.setValidityTime(new Date());
        chamberMapper.insert(shoppingChamber);
        verify(shoppingChamber, shoppingChamber.getId());
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

    public Result verify(ShoppingChamber shoppingChamber, Integer id) {
        String username = shoppingChamber.getTelephone();
        if (username == null) {
            return new Result(201, "没有填写手机号,审核失败", false);
        }
        if (shoppingChamber.getStatus() != null) {

            if (2 == shoppingChamber.getStatus()) {
                return new Result(201, "重复审核", false);
            }
        }
        shoppingChamber.setStatus(2);
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
        ShoppingNotice notice = new ShoppingNotice();
        notice.setCreateName("平台管理员");
        notice.setCreateId("0");
        notice.setType(1);
        notice.setTitle("商会申请通过");
        notice.setAddtime(new Date());
        String content="您申请的智汇玉龙商会已经通过了,网址:http://zhyl.zh0476.com:9002,用户名是" + username + ",初始密码是:" + "zhyl@123";
        notice.setContent(content);
        noticeMapper.insert(notice);
        send(username,content);
        return new Result(ResultCode.SUCCESS);
    }

    public List<BusinessAdmin> findBusinessAdminByUserName(String username) {
        Example example = new Example(BusinessAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        return businessAdminMapper.selectByExample(example);
    }
 public  void  send(String mobile,String content){
     String url = "http://sms.linyu106.com/sms.aspx?action=send&userid=3156&account=15055153559&password=123456&mobile=" + mobile + "&content=【智汇玉龙】" + content + "&sendTime=&extno=";
     Map map = new HashMap();
     ResponseEntity<String> post = restTemplate.postForEntity(url, map, String.class);
   //  System.out.println(post);

 }

    @Override
    public boolean checkTelephone(String telephone) {
        List<BusinessAdmin> list = findBusinessAdminByUserName(telephone);
        if (list.isEmpty()){
            return  true;

        }
        return false;
    }
}
