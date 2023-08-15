package com.oracle.application.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 王飞龙
 * @Date 2023/8/15 14:57
 * @Version 1.0
 */
@FeignClient(name = "cloud-member-provider",path = "/users")
public interface UserFeign {

    @GetMapping("/users/{id}")
    public Users get(@PathVariable("id")Integer id);
}
