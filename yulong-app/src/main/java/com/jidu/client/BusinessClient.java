package com.jidu.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-13 10:34
 */
@FeignClient(value = "business")
public interface BusinessClient {


}
