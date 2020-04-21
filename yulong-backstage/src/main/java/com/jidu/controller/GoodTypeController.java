package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.aop.LogAnno;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.goods.GoodsType;
import com.jidu.service.GoodsTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-05 15:37
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/goodsType")
@Api(value = "商品类型", description = "商品类型")
public class GoodTypeController extends BusinessBaseController {
    @Autowired
    private GoodsTypeService goodsTypeService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加商品类型")
    @LogAnno(operateType = "添加商品类型")
    @RequiresPermissions("type_add")
    public Result save(@RequestBody GoodsType goodsType) {
        Integer integer = goodsTypeService.save("0", goodsType);
        if (integer==-1){
            return  new Result(201,"重复加入",false);
        }
        return new Result(ResultCode.SUCCESS, integer);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改商品类型")
    @RequiresPermissions("type_edit")
    public Result update(@RequestBody GoodsType goodsType) {
        goodsTypeService.update(goodsType);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商品类型")
    @RequiresPermissions("type_find")
    public Result findById(@PathVariable int id) {
        GoodsType goodsType = goodsTypeService.findById(id);
        return new Result(ResultCode.SUCCESS, goodsType);
    }

    @RequestMapping(value = "/findOneLevel/{parentId}", method = RequestMethod.GET)
    @ApiOperation(value = "按照父级id查询商品类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "父级id 一级传1", required = true, paramType = "path")
    })
    public Result<GoodsType> findOneLevel(@PathVariable Integer parentId) {
        List<GoodsType> goodsType = goodsTypeService.findOneLevel("0", parentId);
        return new Result(ResultCode.SUCCESS, goodsType);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除商品类型")
    @RequiresPermissions("type_delete")
    public Result delete(@PathVariable int id) {
        //如果有下级分类不能删除
        List<GoodsType> goodsType = goodsTypeService.findOneLevel("0", id);
        if (!goodsType.isEmpty()) {
            return new Result(201, "存在下级分类不能删除", false);
        }
        goodsTypeService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商品类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path"),
            @ApiImplicitParam(name = "param", value = "条件查询", required = false, paramType = "query")
    })
    @RequiresPermissions("type_find")
    public PageResult search(@PathVariable int pageNum, @PathVariable int pageSize, @RequestParam(required = false) Map param) {
        Page<GoodsType> page = PageHelper.startPage(pageNum, pageSize);
        List<GoodsType> goodsTypes = goodsTypeService.search(param);
        return new PageResult(page.getTotal(), page.getResult());
    }

}
