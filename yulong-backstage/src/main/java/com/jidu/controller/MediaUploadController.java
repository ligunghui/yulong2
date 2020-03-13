package com.jidu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jidu.entity.PageResult;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.pojo.media.MediaFile;
import com.jidu.pojo.sys.AboutUs;
import com.jidu.service.MediaUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @program: yulong
 * @description: 断点续传上传 WebUploader
 * @author: LiGuangHui
 * @create: 2020-02-09 14:02
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/media/upload")
@Api(value = "断点续传上传 WebUploader", description = "断点续传上传 WebUploader")
public class MediaUploadController {
    @Autowired
    private MediaUploadService mediaUploadService;

    @ApiOperation("文件上传注册")
    @PostMapping("/register")
    public Result register(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {
        return mediaUploadService.register(fileMd5, fileName, fileSize, mimetype, fileExt);
    }

    @ApiOperation("分块检查")
    @PostMapping("/checkchunk")
    public Result checkchunk(String fileMd5, Integer chunk, Integer chunkSize) {
        return mediaUploadService.checkchunk(fileMd5, chunk, chunkSize);
    }

    @ApiOperation("上传分块")
    @PostMapping("/uploadchunk")
    public Result uploadchunk(MultipartFile file, String fileMd5, Integer chunk) {
        return mediaUploadService.uploadchunk(file, fileMd5, chunk);
    }

    @ApiOperation("合并文件")
    @PostMapping("/mergechunks")
    public Result mergechunks(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {
        return mediaUploadService.mergechunks(fileMd5, fileName, fileSize, mimetype, fileExt);
    }
    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "查询关于录播视频")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "path")
    })
    public PageResult<MediaFile> search(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        Page<MediaFile> page = PageHelper.startPage(pageNum, pageSize);
        List<MediaFile> mediaFiles = mediaUploadService.search();
        return new PageResult(page.getTotal(), page.getResult());
    }
    @RequestMapping(value = "/{fileMd5}/{videoName}/{fileImg}", method = RequestMethod.POST)
    @ApiOperation(value = "添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileMd5", value = "fileMd5", required = true, paramType = "path"),
            @ApiImplicitParam(name = "videoName", value = "视频名称", required = true, paramType = "path"),
            @ApiImplicitParam(name = "fileImg", value = "封面", required = true, paramType = "path")
    })
    public Result add(@PathVariable String fileMd5, @PathVariable String videoName, @PathVariable String fileImg) {

        return mediaUploadService.add(fileMd5,videoName,fileImg);
    }
    @RequestMapping(value = "/{fileId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileId", value = "fileId", required = true, paramType = "path"),
    })
    public Result delete(@PathVariable String fileId) {

        return mediaUploadService.delete(fileId);
    }
}
