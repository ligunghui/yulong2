package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.goods.GiftPack;
import com.jidu.service.GiftPackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-23 10:21
 */
@CrossOrigin
@RestController
@RequestMapping(value="/backstage/giftPack")
@Api(value = "礼包专区", description = "礼包专区")
public class GiftPackController {
    @Autowired
    private GiftPackService giftPackService;
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加礼包专区")
    public Result save(@RequestBody GiftPack giftPack) {
        giftPackService.save(giftPack);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改礼包专区")
    public Result update(@RequestBody GiftPack giftPack) {
        giftPackService.update(giftPack);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询礼包专区")
    public Result<GiftPack>  findById(@PathVariable long id) {
        GiftPack giftPack= giftPackService.findById(id);
        return new Result(ResultCode.SUCCESS,giftPack);
    }
   

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除礼包专区")
    public Result delete(@PathVariable long id) {
        giftPackService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询礼包专区")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNum",value="当前页码",required=true,paramType="path"),
            @ApiImplicitParam(name="pageSize",value="每页条数",required=true,paramType="path")
    })
    public PageResult<GiftPack> search (@PathVariable int pageNum, @PathVariable int pageSize, @RequestParam(required = false) Map param) {
        Page<GiftPack> page = PageHelper.startPage(pageNum, pageSize);
        List<GiftPack> giftPack= giftPackService.search(param);
        return new PageResult(page.getTotal(),page.getResult());
    }
}

