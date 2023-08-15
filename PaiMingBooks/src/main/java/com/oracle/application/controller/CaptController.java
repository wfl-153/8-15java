package com.oracle.application.controller;

import com.oracle.application.config.RedisConfig;
import com.oracle.application.entity.vo.Result;
import com.oracle.application.utils.CaptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author 王飞龙
 * @Date 2023/8/14 13:18
 * @Version 1.0
 */
@RestController
public class CaptController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisConfig redisConfig;

    @GetMapping("/getCapt")
    public Result getCapt() {
        try {
            Map<String, String> map = CaptUtils.createCaptString();
            //系统生成一个标识 根据标识判断是哪一个用户
            String uuid = UUID.randomUUID().toString();
            //将生成的标识保存到redis
            String userKeyPrefix = redisConfig.getUserKeyPrefix() + "login:capt:" + uuid;
            //获取验证码的值
            String codeValue = map.get("captCodeValue");
            //保存到redis里面 设置过期时间 10分
            this.redisTemplate.boundValueOps(userKeyPrefix).set(codeValue, 10, TimeUnit.MINUTES);
            Map<String, String> stringMap = new HashMap<>();
            //设置一个map用来保存生成的标识和生成图片的值
            stringMap.put("requestId", uuid);
            stringMap.put("img", map.get("base64Value"));
            //返回
            return Result.success(stringMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
