package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.comment.UserComment;
import com.jidu.service.UserCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: liguanghui
 * Date: 2020/3/9 0009 下午 2:59
 * @Version:
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/app/comment")
@Api(value = "用户评论", description = "用户评论")
public class UserCommentController extends BaseController {
    @Autowired
    private UserCommentService userCommentService;

    @RequestMapping(value = "/{content}/{itemId}", method = RequestMethod.GET)
    @ApiOperation(value = "添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "内容", required = true, paramType = "path"),
            @ApiImplicitParam(name = "itemId", value = "商品id,新闻id 评论id等", required = true, paramType = "path"),
    })
    public Result addComment(@PathVariable String content, @PathVariable String itemId) {
        UserComment userComment = new UserComment();
        userComment.setUserId(userId);
        userComment.setUserName(userName);
        userComment.setUserPic(userPhoto);
        userComment.setItemId(itemId);
        userComment.setContent(content);
        userCommentService.addComment(userComment);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "评论id", required = true, paramType = "path"),
    })
    public Result thumbs( @PathVariable Integer id) {
        userCommentService.thumbs(id);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "评论id", required = true, paramType = "path"),
    })
    public Result delete( @PathVariable Integer id) {
        UserComment userComment = userCommentService.findById(id);
        if (!userComment.getUserId().equals(userId)){
            return new Result(201,"不是本人",false);
        }
        userCommentService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }
}
