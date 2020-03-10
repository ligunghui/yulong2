package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.service.GiftCollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-25 10:13
 */
@CrossOrigin
@RestController
@RequestMapping(value="/app/giftCollect")
@Api(value = "商品收藏", description = "商品收藏")
public class GiftCollectController extends BaseController{
    @Autowired
    private GiftCollectService giftCollectService;
    @RequestMapping(value = "/addGiftCollect/{goodsId}", method = RequestMethod.GET)
    @ApiOperation(value = "用户收藏商品")
    public Result addGiftCollect(@PathVariable Long goodsId) {
        giftCollectService.addGiftCollect(goodsId,userId);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/deleteGiftCollect/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "用户删除收藏")
    public Result deleteGiftCollect(@PathVariable Long id) {
        giftCollectService.deleteGiftCollect(id);
        return new Result(ResultCode.SUCCESS);
    }
}
