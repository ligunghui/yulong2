package com.jidu.service.impl;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.mapper.BusinessOpportunitiesMapper;
import com.jidu.pojo.opportunities.BusinessOpportunities;
import com.jidu.service.OpportunitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: yulong
 * @author: LiGuangHui
 * @create: 2020-02-18 18:51
 * @description:
 */
@Service
public class OpportunitiesServiceImpl implements OpportunitiesService {
    @Autowired
    private BusinessOpportunitiesMapper businessOpportunitiesMapper;

    @Override
    public void addOpportunities(BusinessOpportunities businessOpportunities) {
        businessOpportunitiesMapper.insert(businessOpportunities);
    }

    @Override
    public Result findOpportunitiesUserId(String userId, String type) {
        Example example=new Example(BusinessOpportunities.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("type",type);
        List<BusinessOpportunities> businessOpportunities = businessOpportunitiesMapper.selectByExample(example);
        return new Result(ResultCode.SUCCESS, businessOpportunities);
    }
}
