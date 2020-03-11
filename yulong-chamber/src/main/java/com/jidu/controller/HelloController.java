package com.jidu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: liguanghui
 * Date: 2020/3/11 0011 下午 1:53
 * @Version:
 * @Description:
 */
@Controller
public class HelloController {
    @RequestMapping("/")
    public String hello() {
        return "forward:login.html";
    }
}
