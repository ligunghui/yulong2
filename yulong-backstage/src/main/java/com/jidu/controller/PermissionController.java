package com.jidu.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-27 17:22
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/backstage/permission")
@Api(value = "权限", description = "权限")
public class PermissionController {
}
