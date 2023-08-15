package com.oracle.application.controller;

import com.alibaba.fastjson.JSONObject;
import com.oracle.application.config.RedisConfig;
import com.oracle.application.entity.vo.Result;
import com.oracle.application.utils.CaptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author 王飞龙
 * @Date 2023/8/14 14:44
 * @Version 1.0
 */
@Controller
public class LoginController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisConfig redisConfig;

    @PostMapping("/login")
    public Result login(String  requestCapt,String requestId) {
        String key=this.redisConfig.getUserKeyPrefix()+"login:capt:"+requestId;
        String code = redisTemplate.boundValueOps(key).get();
        if ("".equals(requestCapt) || requestCapt==null || !code.equalsIgnoreCase(requestCapt) )
        {
            System.out.println("验证码有误");
        }else {
            System.out.println("验证码正确");
        }
        return Result.failed();
    }
}
