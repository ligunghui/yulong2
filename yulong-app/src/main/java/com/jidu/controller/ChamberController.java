package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.group.ChamberDetails;
import com.jidu.service.ChamberService;
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
 * @create: 2020-02-14 12:38
 */
@CrossOrigin
@RestController
@RequestMapping(value="/app/chamber")
@Api(value = "商会", description = "商会")
public class ChamberController {
    @Autowired
    private ChamberService chamberService;
    @RequestMapping(value = "/{chamberId}", method = RequestMethod.GET)
    @ApiOperation(value = "商会详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chamberId", value = "商会id", required = true, paramType = "path")
    })
    public Result<ChamberDetails> findChamberDetails(@PathVariable Integer chamberId) {
        ChamberDetails chamberDetails= chamberService.findChamberDetails(chamberId);
        return new Result(ResultCode.SUCCESS,chamberDetails);
    }
}
