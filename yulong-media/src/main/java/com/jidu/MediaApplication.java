package com.jidu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-09 13:55
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.jidu.mapper")
@ServletComponentScan
public class MediaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MediaApplication.class, args);
    }
}
