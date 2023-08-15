package com.oracle.application.config;

import com.oracle.application.interceptor.MyIntercept;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author 王飞龙
 * @Date 2023/8/12 10:36
 * @Version 1.0
 */
@Configuration
public class InterceptConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
        registry.addInterceptor(new MyIntercept()).addPathPatterns("/books/list");
    }
}
