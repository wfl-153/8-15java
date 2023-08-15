package com.oracle.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author 王飞龙
 * @Date 2023/8/15 15:23
 * @Version 1.0
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ConSumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConSumerApplication.class,args);
    }
}
