package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.group.IndexGroup;
import com.jidu.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/chamber/index")
public class IndexController extends BusinessBaseController {
    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Result<IndexGroup> findAll() {

        IndexGroup indexGroup = indexService.findAll(storeId);
        indexGroup.setUserName(userName);
        return new Result(ResultCode.SUCCESS, indexGroup);
    }
}
