package com.oracle.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author 王飞龙
 * @Date 2023/8/13 14:12
 * @Version 1.0
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域访问的路径
                .allowedOrigins("*")   // 允许跨域访问的源
                //.allowedOrigins("http://localhost")   // 允许跨域访问的源
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE") // 允许请求方法
                .maxAge(168000)    // 预检间隔时间
                .allowedHeaders("*")  // 允许头部设置
                .allowCredentials(true);   // 是否发送cookie
    }

}
