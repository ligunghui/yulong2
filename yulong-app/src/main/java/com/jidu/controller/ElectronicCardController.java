package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.card.ElectronicCard;
import com.jidu.service.ElectronicCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-12 10:45
 */
@CrossOrigin
@RestController
@RequestMapping(value="/app/electronicCard")
@Api(value = "电子名片", description = "电子名片")
public class ElectronicCardController extends  BaseController{
    @Autowired
    private ElectronicCardService electronicCardService;
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "用户添加电子名片")
    public Result addElectronicCard(@RequestBody ElectronicCard electronicCard) {
        electronicCard.setUserId(userId);
        electronicCardService.addElectronicCard(electronicCard);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "用户编辑电子名片")
    public Result editElectronicCard(@RequestBody ElectronicCard electronicCard) {
        electronicCard.setUserId(userId);
        electronicCardService.editElectronicCard(electronicCard);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "我的电子名片")
    public Result<ElectronicCard> findElectronicCard() {
        ElectronicCard electronicCard= electronicCardService.findElectronicCard(userId);
        return new Result(ResultCode.SUCCESS,electronicCard);
    }
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除我的电子名片")
    public Result deleteElectronicCard() {
         electronicCardService.deleteElectronicCard(userId);
        return new Result(ResultCode.SUCCESS);
    }
}
