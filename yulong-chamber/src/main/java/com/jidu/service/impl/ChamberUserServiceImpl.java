package com.jidu.service.impl;

import com.jidu.mapper.ChamberMapper;
import com.jidu.mapper.ChamberUserMapper;
import com.jidu.pojo.shop.ChamberUser;
import com.jidu.service.ChamberUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-11 21:05
 */
@Service
public class ChamberUserServiceImpl implements ChamberUserService {
    @Autowired
    private ChamberUserMapper chamberUserMapper;
    @Autowired
    private ChamberMapper chamberMapper;

    @Override
    public void apply(ChamberUser chamberUser) {
        chamberUserMapper.insert(chamberUser);
    }

    @Override
    public void cancelChamberByUserId(Integer id) {
        chamberUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<ChamberUser> findChamberUserByChamberId(Integer chamberId) {
        Example example = new Example(ChamberUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("chamberId", chamberId);
        return chamberUserMapper.selectByExample(example);
    }

    @Override
    public void delChamberUser(Integer id, Integer status, String violationReseaon, String userName) {
        ChamberUser chamberUser = chamberUserMapper.selectByPrimaryKey(id);
        if (status == 2 && !StringUtils.isEmpty(violationReseaon)) {
            chamberUser.setReason(violationReseaon);
        }
        chamberUser.setStatus(status);
        chamberUser.setHandlerTime(new Date());
        chamberUser.setHandlerPeople(userName);
        chamberUserMapper.updateByPrimaryKeySelective(chamberUser);
    }

    @Override
    public ChamberUser findChamberUserById(Integer id) {
        return chamberUserMapper.selectByPrimaryKey(id);
    }

}
