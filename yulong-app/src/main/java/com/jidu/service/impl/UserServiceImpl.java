package com.jidu.service.impl;

import com.jidu.mapper.UserInfoMapper;
import com.jidu.pojo.sys.UserInfo;
import com.jidu.service.UserService;
import com.jidu.utils.IdWorker;
import com.jidu.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public UserInfo findByMobile(String mobile) {
        UserInfo userInfo = new UserInfo();
        userInfo.setMobile(mobile);
        return userInfoMapper.selectOne(userInfo);
    }

    @Override
    public void save(UserInfo userInfo) {
        String id = idWorker.nextId() + "";
        userInfo.setId(id);
        String invitationCode = id.substring(id.length() - 6);
        userInfo.setInvitationCode(invitationCode);
        userInfo.setAddtime(new Date());
        userInfo.setDeletestatus(false);
        userInfo.setLastlogindate(new Date());
        userInfo.setLogincount(1);
        userInfo.setUserCredit(5);
        userInfo.setUsername(userInfo.getMobile());
        userInfo.setWalletMoney(new BigDecimal("0"));
        userInfo.setAuthentication(0);
        userInfo.setChamberId(0);
        userInfo.setIntegral(0);
        userInfo.setVipIs(0);
        userInfo.setGradeId(1);
        userInfoMapper.insert(userInfo);
    }

    @Override
    public void update(UserInfo userInfo) {
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserInfo> search(Map param) {
        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();
        return userInfoMapper.selectByExample(example);
    }

    @Override
    public String sendCode(String mobile) {
        Map map = new HashMap();
        int a = (int) ((Math.random() * 9.0D + 1.0D) * 100000.0D);
        String random = a + "";
        String url = "http://sms.linyu106.com/sms.aspx?action=send&userid=3156&account=15055153559&password=123456&mobile=" + mobile + "&content=【智汇玉龙】您本次的验证码为" + random + "&sendTime=&extno=";

        restTemplate.postForEntity(url, map, String.class);

        return random;
    }

    @Override
    public UserInfo getByWxOpenID(String openID, String wxName, HttpServletRequest request) {
        UserInfo userInfo = new UserInfo();
        userInfo.setWxopenid(openID);
        UserInfo u = userInfoMapper.selectOne(userInfo);
        if (u != null) {
            return u;
        }
        userInfo.setId(idWorker.nextId() + "");
        userInfo.setUsername(wxName);
        userInfo.setLastlogindate(new Date());
        userInfo.setLastloginip(IpUtil.getLocalIp(request));
        userInfo.setWalletMoney(new BigDecimal(0.0D));
        userInfoMapper.insert(userInfo);
        return userInfo;
    }

    @Override
    public UserInfo findByInvitationCode(String invitationCode) {
        UserInfo userInfo = new UserInfo();
        userInfo.setInvitationCode(invitationCode);
        return userInfoMapper.selectOne(userInfo);
    }


}
