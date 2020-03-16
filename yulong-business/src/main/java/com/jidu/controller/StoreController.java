package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.shop.BusinessAdmin;
import com.jidu.pojo.shop.ShoppingStore;
import com.jidu.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-06 19:15
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/business/store")
@Api(value = "商户操作", description = "商户操作")
public class StoreController extends BusinessBaseController {
    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改商户")
    public Result update(@RequestBody ShoppingStore shoppingStore) {
        storeService.update(shoppingStore);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询商户")
    public Result<ShoppingStore> findById() {
        ShoppingStore shoppingStore = storeService.findById(storeId);
        return new Result(ResultCode.SUCCESS, shoppingStore);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除商户")
    //@RequiresPermissions(value = "delete-store")
    public Result delete(@PathVariable String id) {
        storeService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/updateBusinessAdmin", method = RequestMethod.PUT)
    @ApiOperation(value = "修改商户管理员用户名 密码")
    public Result updateBusinessAdmin(@RequestBody BusinessAdmin businessAdmin) {
        storeService.updateBusinessAdmin(businessAdmin);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/addBusinessAdmin", method = RequestMethod.POST)
    @ApiOperation(value = "添加商户管理员")
    public Result addBusinessAdmin(@RequestBody BusinessAdmin businessAdmin) {
        businessAdmin.setStoreId(storeId);
       List<BusinessAdmin> list= storeService.findBusinessAdminByUserName(businessAdmin.getUsername());
       if (!list.isEmpty()){
           return new Result(201,"用户名重复",false);

       }
        storeService.addBusinessAdmin(businessAdmin);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/findBusinessAdmin/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询商户管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    public PageResult<BusinessAdmin> findBusinessAdmin(@PathVariable int pageNum, @PathVariable int pageSize) {
        Page<BusinessAdmin> page = PageHelper.startPage(pageNum, pageSize);
       List<BusinessAdmin> businessAdmins= storeService.findBusinessAdmin(storeId);
        return new PageResult(page.getTotal(), page.getResult());
    }
    @RequestMapping(value = "/findBusinessAdminById/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询单个商户管理员")
    public Result<BusinessAdmin> findBusinessAdminById(@PathVariable int id) {
        BusinessAdmin businessAdmins= storeService.findBusinessAdminById(id);
        return new Result(ResultCode.SUCCESS,businessAdmins);
    }
    @RequestMapping(value = "/deleteBusinessAdminById/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除单个商户管理员")
    public Result deleteBusinessAdminById(@PathVariable int id) {
       storeService.deleteBusinessAdminById(id);
        return new Result(ResultCode.SUCCESS);
    }
}


