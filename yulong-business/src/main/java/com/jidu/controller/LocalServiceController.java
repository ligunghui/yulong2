package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.Tree;
import com.jidu.pojo.shop.ShoppingStore;
import com.jidu.service.LocalServiceService;
import com.jidu.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: liguanghui
 * Date: 2020/3/25 0025 下午 2:24
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/business/localService")
@Api(value = "本地服务操作", description = "本地服务操作")
public class LocalServiceController extends BusinessBaseController {
    @Autowired
    private LocalServiceService localServiceService;
    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询树形结构")
    public Result<Tree> findAll() {
        return localServiceService.findAll(storeId);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加")
    public Result add(@RequestParam(value = "typeIds[]") Integer[] typeIds) {
        ShoppingStore shoppingStore = storeService.findById(storeId);

        localServiceService.add(storeId, typeIds);
        return new Result(ResultCode.SUCCESS);
    }
}
