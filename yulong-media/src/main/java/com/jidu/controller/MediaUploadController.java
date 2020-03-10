package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.service.MediaUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
}
