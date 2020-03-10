package com.jidu.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "backstage")
public interface BackstageClient {

}
