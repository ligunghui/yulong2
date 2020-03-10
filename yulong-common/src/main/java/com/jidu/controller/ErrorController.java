package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Api(value = "公共错误跳转", description = "公共错误跳转")
public class ErrorController {

    //公共错误跳转
    @RequestMapping(value="autherror")
    public Result autherror(int code) {
        // System.out.println(code);
        return code ==1?new Result(ResultCode.UNAUTHENTICATED):new Result(ResultCode.UNAUTHORISE);
    }

}