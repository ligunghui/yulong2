package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.annotation.ApiJsonObject;
import com.jidu.annotation.ApiJsonProperty;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.sys.UserInfo;
import com.jidu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-11 14:36
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/app/user")
@Api(value = "用户操作", description = "用户操作")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录")
    public Result login(@ApiJsonObject(name = "loginMap", value = {
            @ApiJsonProperty(key = "mobile", example = "18614242538", description = "mobile"),
            @ApiJsonProperty(key = "password", example = "123456", description = "password")
    }) @RequestBody Map<String, String> loginMap) {
        String mobile = loginMap.get("mobile");
        String password = loginMap.get("password");
        try {
            //加密密码
            password = new Md5Hash(password, mobile, 3).toString();  //1.密码，盐，加密次数
            UsernamePasswordToken upToken = new UsernamePasswordToken(mobile, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(upToken);
            String sessionId = (String) subject.getSession().getId();
            return new Result(ResultCode.SUCCESS, sessionId);
        } catch (Exception e) {
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册(用户名 密码)")
    public Result save(@ApiJsonObject(name = "registerMap", value = {
            @ApiJsonProperty(key = "mobile", example = "18614242538", description = "mobile"),
            @ApiJsonProperty(key = "password", example = "123456", description = "password"),
            @ApiJsonProperty(key = "invitationCode", example = "123456", description = "invitationCode"),
            @ApiJsonProperty(key = "code", example = "123456", description = "code")})
                       @RequestBody Map<String, String> map) {
        String mobile = map.get("mobile");
        String password = map.get("password");
        String invitationCode = map.get("invitationCode");
        String checkCode = checkCode(map);
        if (!StringUtils.isEmpty(checkCode)) {
            return new Result(2003, checkCode, false);

        }
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {

            return new Result(ResultCode.MOBILNULLASSWORDNULL);
        }
        UserInfo info = userService.findByMobile(mobile);
        if (Objects.nonNull(info)) {

            return new Result(2003, "手机号已被注册", false);
        }
        if (!StringUtils.isEmpty(invitationCode)) {

            UserInfo invitationInfo = userService.findByInvitationCode(invitationCode);
            if (Objects.isNull(invitationInfo)) {
                return new Result(2003, "邀请码错误", false);
            }
        }
        UserInfo userInfo = new UserInfo();
        password = new Md5Hash(password, mobile, 3).toString();
        userInfo.setPassword(password);
        userInfo.setMobile(mobile);
        userService.save(userInfo);
        return new Result(ResultCode.SUCCESS);

    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    @ApiOperation(value = "忘记密码")
    public Result forgotPassword(
            @ApiJsonObject(name = "forgotPasswordMap", value = {
                    @ApiJsonProperty(key = "mobile", example = "18614242538", description = "mobile"),
                    @ApiJsonProperty(key = "password", example = "123456", description = "password"),
                    @ApiJsonProperty(key = "code", example = "123456", description = "code")})
            @RequestBody Map<String, String> map) {
        String mobile = map.get("mobile");
        String password = map.get("password");
        UserInfo userInfo = userService.findById(userId);
        if (!userInfo.getMobile().equals(mobile)) {
            return new Result(2003, "绑定的手机号错误", false);

        }
        String checkCode = checkCode(map);
        if (!StringUtils.isEmpty(checkCode)) {
            return new Result(2003, checkCode, false);

        }
        password = new Md5Hash(password, mobile, 3).toString();  //1.密码，盐，加密次数
        userInfo.setPassword(password);
        userService.update(userInfo);
        return new Result(ResultCode.SUCCESS);

    }

    @RequestMapping(value = "/forgotWalletPassword", method = RequestMethod.POST)
    @ApiOperation(value = "忘记钱包密码")
    public Result forgotWalletPassword(
            @ApiJsonObject(name = "pojo", value = {
                    @ApiJsonProperty(key = "mobile", example = "18614242538", description = "mobile"),
                    @ApiJsonProperty(key = "walletPassword", example = "123456", description = "walletPassword"),
                    @ApiJsonProperty(key = "code", example = "123456", description = "code")})
            @RequestBody Map<String, String> map) {
        String mobile = map.get("mobile");
        String walletPassword = map.get("walletPassword");
        UserInfo userInfo = userService.findById(userId);
        if (!userInfo.getMobile().equals(mobile)) {
            return new Result(2003, "绑定的手机号错误", false);

        }
        String checkCode = checkCode(map);
        if (!StringUtils.isEmpty(checkCode)) {
            return new Result(2003, checkCode, false);

        }
        walletPassword = new Md5Hash(walletPassword, mobile, 3).toString();  //1.密码，盐，加密次数
        userInfo.setWalletPassword(walletPassword);
        userService.update(userInfo);
        return new Result(ResultCode.SUCCESS);

    }

    @RequestMapping(value = "/updateMobile", method = RequestMethod.POST)
    @ApiOperation(value = "修改手机号")
    public Result updateMobile(
            @ApiJsonObject(name = "updateMobileMap", value = {
                    @ApiJsonProperty(key = "oldMobile", example = "18614242538", description = "oldMobile"),
                    @ApiJsonProperty(key = "mobile", example = "18614242538", description = "mobile"),
                    @ApiJsonProperty(key = "password", example = "18614242538", description = "password"),
                    @ApiJsonProperty(key = "code", example = "123456", description = "code")})
            @RequestBody Map<String, String> map) {
        String oldMobile = map.get("oldMobile");
        String mobile = map.get("mobile");
        String password = map.get("password");
        UserInfo userInfo = userService.findById(userId);
        if (!userInfo.getMobile().equals(oldMobile)) {
            return new Result(2003, "绑定的手机号错误", false);

        }
        String checkCode = checkCode(map);
        if (!StringUtils.isEmpty(checkCode)) {
            return new Result(2003, checkCode, false);

        }
        password = new Md5Hash(password, mobile, 3).toString();  //1.密码，盐，加密次数
        userInfo.setPassword(password);
        userService.update(userInfo);
        return new Result(ResultCode.SUCCESS);

    }

    @ApiOperation("用户实名认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "真实姓名", required = true, paramType = "path"),
            @ApiImplicitParam(name = "cardImg", value = "身份证正面", required = true, paramType = "path"),
            @ApiImplicitParam(name = "reverseImg", value = "身份证反面", required = true, paramType = "path"),
            @ApiImplicitParam(name = "certificate", value = "身份证号", required = true, paramType = "path"),
    })
    @RequestMapping(value = "RealName/{name}/{cardImg}/{reverseImg}/{certificate}", method = RequestMethod.GET)
    public Result RealName(@PathVariable String name, @PathVariable String cardImg, @PathVariable String reverseImg, @PathVariable String certificate) {
        UserInfo userInfo = userService.findById(userId);
        if (userInfo.getAuthentication() == 1) {
            return new Result(1000, "您已经存在一条记录", false);
        }

        userInfo.setTruename(name);
        userInfo.setCardImg(cardImg);
        userInfo.setReverseImg(reverseImg);
        userInfo.setCertificate(certificate);
        userInfo.setAuthentication(1);
        userService.update(userInfo);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改用户")
    public Result update(@RequestBody UserInfo userInfo) {
        System.out.println(userInfo);
        userInfo.setId(userId);
        userService.update(userInfo);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据id查询用户")
    public Result<UserInfo> findById(@PathVariable String id) {
        UserInfo userInfo = userService.findById(id);
        return new Result(ResultCode.SUCCESS, userInfo);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户id")
    public Result findId() {
        return new Result(ResultCode.SUCCESS, userId);
    }

    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    public PageResult search(@PathVariable int pageNum, @PathVariable int pageSize, @RequestParam(required = false) Map param) {
        Page<UserInfo> page = PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> userInfos = userService.search(param);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @ApiOperation("发送验证码")
    @RequestMapping(value = {"/sendCode/{mobile}"}, method = {RequestMethod.GET})
    @ApiImplicitParams({@ApiImplicitParam(name = "mobile", value = "手机号", dataType = "string", paramType = "path")})
    public Result sendCode(@PathVariable String mobile) {
        try {
            String sendCode = userService.sendCode(mobile);
            this.redisTemplate.opsForValue().set(mobile, sendCode, 300, TimeUnit.SECONDS);
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.FAIL);
    }

    @ApiOperation("微信登录")
    @RequestMapping(value = {"/getByWxOpenID/{openID}/{wxName}"}, method = {RequestMethod.GET})
    @ApiImplicitParams({@ApiImplicitParam(name = "openID", value = "openID", dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "wxName", value = "微信昵称", dataType = "string", paramType = "path")})
    public Result getByOpenID(@PathVariable String openID, @PathVariable String wxName) {
        UserInfo userInfo = userService.getByWxOpenID(openID, wxName, request);
        if (StringUtils.isEmpty(userInfo.getPhone())) {
            return new Result(201, "未绑定手机号", false);
        }

        return new Result(200, "已绑定手机号", true);
    }

    private String checkCode(Map<String, String> map) {
        String mobile = map.get("mobile");
        String code = map.get("code");
        if (StringUtils.isEmpty(code)) {
            return "验证码不能为空";
        }
        String oldCode = this.redisTemplate.opsForValue().get(mobile);
        if (StringUtils.isEmpty(oldCode)) {
            return "还没发送验证码或者验证码已过期";
        }
        if (!oldCode.equals(code)) {
            return "验证码错误";

        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Md5Hash("admin", "yulong@123", 3).toString());
    }

}


