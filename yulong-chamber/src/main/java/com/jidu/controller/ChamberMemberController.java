package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.shop.ChamberMember;
import com.jidu.service.ChamberMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/4/2 0002 上午 9:17
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/chamber/member")
@Api(value = "商会成员", description = "商会成员")
public class ChamberMemberController extends BusinessBaseController{
    @Autowired
    private ChamberMemberService chamberMemberService;
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加商会成员")
  
    public Result save(@RequestBody ChamberMember chamberMember) {
        chamberMember.setChamberId(storeId);
        chamberMemberService.save(chamberMember);

        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改商会成员")
    public Result update(@RequestBody ChamberMember chamberMember) {
        chamberMemberService.update(chamberMember);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商会成员")
    public Result<ChamberMember> findById(@PathVariable Integer id) {
        ChamberMember chamberMember = chamberMemberService.findById(id);
        return new Result(ResultCode.SUCCESS, chamberMember);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除商会成员")
    public Result delete(@PathVariable Integer id) {
        chamberMemberService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询商会成员")
    public Result<ChamberMember> search() {
        List<ChamberMember> chamberMember = chamberMemberService.search(storeId);
        return new Result(ResultCode.SUCCESS, chamberMember);
    }
}
