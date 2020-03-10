package com.jidu.controller;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-19 16:27
 */
@RestController
@CrossOrigin
@RequestMapping({"/upload"})
@Api(value = "文件上传统一接口", description = "文件上传统一接口")
public class UploadController {
    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";// 中文正则
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @PostMapping({"/upload"})
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new Result(1001, "文件不能为空", false);
        }
        String s = System.currentTimeMillis() + "";
        String fileName = s + file.getOriginalFilename();
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        Matcher mat = pat.matcher(fileName);
        fileName = mat.replaceAll("");
        String os = System.getProperty("os.name");
        String filePath = "/yulong/upload/";
        if (os.toLowerCase().startsWith("win")) {
            filePath = "c:/upload/";
        }

        File dest = new File(filePath + fileName);
        String yuming = "http://39.96.95.40:8080/upload/";
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
            return new Result(ResultCode.SUCCESS, yuming + s + fileName);
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return new Result(1001, "文件上传失败", false);
    }

    public static void main(String[] args) {
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        String fileName = "已一抬头";
        Matcher mat = pat.matcher(fileName);
        fileName = mat.replaceAll("");
        System.out.println(fileName);
    }
}
