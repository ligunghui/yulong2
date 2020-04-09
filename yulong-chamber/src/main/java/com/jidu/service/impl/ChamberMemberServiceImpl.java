package com.jidu.service.impl;

import com.jidu.mapper.ChamberMemberMapper;
import com.jidu.pojo.activity.ShoppingActivity;
import com.jidu.pojo.shop.ChamberMember;
import com.jidu.service.ChamberMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/4/2 0002 上午 9:22
 * @Version:
 * @Description:
 */
@Service
public class ChamberMemberServiceImpl implements ChamberMemberService {
    @Autowired
    private ChamberMemberMapper chamberMemberMapper;
    @Override
    public void save(ChamberMember chamberMember) {
        chamberMember.setAddtime(new Date());
        chamberMemberMapper.insert(chamberMember);
    }

    @Override
    public void update(ChamberMember chamberMember) {
        chamberMemberMapper.updateByPrimaryKeySelective(chamberMember);
    }

    @Override
    public ChamberMember findById(Integer id) {
        return chamberMemberMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
        chamberMemberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<ChamberMember> search(String storeId) {
        Example example = new Example(ChamberMember.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("chamberId", storeId);
        example.setOrderByClause("addtime DESC");
        return chamberMemberMapper.selectByExample(example);
    }
}
