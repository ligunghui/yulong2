package com.jidu.service;

import com.jidu.entity.Result;
import com.jidu.pojo.opportunities.BusinessOpportunities;

public interface OpportunitiesService {
    void addOpportunities(BusinessOpportunities businessOpportunities);

    Result findOpportunitiesUserId(String userId, String type);
}
