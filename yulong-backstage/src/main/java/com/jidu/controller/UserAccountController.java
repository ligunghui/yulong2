package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.shop.ShoppingStore;
import com.jidu.pojo.sys.UserAccount;
import com.jidu.service.UserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/15 0015 下午 6:51
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/incom")
@Api(value = "平台收益明细", description = "平台收益明细 ")
@RequiresPermissions("revenue_details")
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result findUserAccount() {
        List<UserAccount> userAccountList = userAccountService.findUserAccount();
        return new Result(ResultCode.SUCCESS, userAccountList);
    }
}
