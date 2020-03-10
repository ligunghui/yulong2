package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.opportunities.BusinessOpportunities;
import com.jidu.service.OpportunitiesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-18 18:46
 */
@CrossOrigin
@RestController
@RequestMapping(value="/app/opportunities")
@Api(value = "汇商机", description = "汇商机")
public class OpportunitiesController extends BaseController {
    @Autowired
    private OpportunitiesService opportunitiesService;
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "用户发布")
    public Result addOpportunities(@RequestBody BusinessOpportunities businessOpportunities) {
        businessOpportunities.setUserId(userId);
        opportunitiesService.addOpportunities(businessOpportunities);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/findOpportunitiesUserId/{type}", method = RequestMethod.GET)
    @ApiOperation(value = "我的求购与供应")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "1供应2求购", required = true, paramType = "path")
    })
    public Result<BusinessOpportunities> findChamberByUserId(@PathVariable String type) {
        return  opportunitiesService.findOpportunitiesUserId(userId,type);
    }
}
