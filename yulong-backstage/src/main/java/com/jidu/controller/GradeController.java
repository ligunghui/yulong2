package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.grade.SysGrade;
import com.jidu.service.GradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/4 0004 上午 11:31
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/grade")
@Api(value = "会员等级", description = "会员等级")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加会员等级")
    public Result save(@RequestBody SysGrade sysGrade) {
        gradeService.save(sysGrade);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改会员等级")
    public Result update(@RequestBody SysGrade sysGrade) {
        gradeService.update(sysGrade);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询会员等级")
    public Result<SysGrade> findById(@PathVariable Integer id) {
        SysGrade sysGrade = gradeService.findById(id);
        return new Result(ResultCode.SUCCESS, sysGrade);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除会员等级")
    public Result delete(@PathVariable Integer id) {
        gradeService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询会员等级")
    public Result<SysGrade> search() {
        List<SysGrade> sysGrade = gradeService.search();
        return new Result(ResultCode.SUCCESS, sysGrade);
    }
}
