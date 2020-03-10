package com.jidu;

import com.jidu.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-11 14:15
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.jidu.mapper")
@EnableFeignClients(basePackages = {"com.jidu.client"})
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
    @Bean
    public RestTemplate restTemplate(){

        RestTemplate restTemplate=new RestTemplate();
        return  restTemplate;
    }

}
