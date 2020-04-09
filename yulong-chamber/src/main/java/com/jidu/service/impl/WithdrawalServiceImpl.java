package com.jidu.service.impl;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.mapper.WithdrawalMapper;
import com.jidu.pojo.withdrawal.WithdrawalApplication;
import com.jidu.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/3/20 0020 上午 11:55
 * @Version:
 * @Description:
 */
@Service
public class WithdrawalServiceImpl implements WithdrawalService {
    @Autowired
    private WithdrawalMapper withdrawalMapper;

    @Override
    public void save(WithdrawalApplication withdrawalApplication) {
        withdrawalApplication.setApplyTime(new Date());
        withdrawalApplication.setStatus(1);
        withdrawalMapper.insert(withdrawalApplication);
    }

    @Override
    public Result delete(Integer id) {
        WithdrawalApplication withdrawalApplication = withdrawalMapper.selectByPrimaryKey(id);
        if (2==withdrawalApplication.getStatus()){
            return  new Result(201,"申请已通过,不能删除",false);
        }
        withdrawalMapper.deleteByPrimaryKey(id);
        return  new Result(ResultCode.SUCCESS);
    }

    @Override
    public List<WithdrawalApplication> search(Map param, String storeId) {
        Example example = new Example(WithdrawalApplication.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uid", storeId);
        return withdrawalMapper.selectByExample(example);
    }
}
