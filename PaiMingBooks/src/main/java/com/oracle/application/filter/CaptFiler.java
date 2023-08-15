package com.oracle.application.filter;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Utf8;
import com.oracle.application.config.RedisConfig;
import com.oracle.application.entity.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author 王飞龙
 * @Date 2023/8/14 13:53
 * @Version 1.0
 */
@WebFilter("/login")
public class CaptFiler implements Filter {


    @Autowired
    private RedisConfig redisConfig;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        response1.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response1.setHeader("Access-Control-Allow-Origin", "*");
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        response1.setHeader("Access-Control-Allow-Methods", "*");
        /* 允许跨域的请求头 */
        response1.setHeader("Access-Control-Allow-Headers", "*");
        String requestCapt = request1.getParameter("requestCapt");
        String requestId = request1.getParameter("requestId");
        System.out.println("requestId==" + requestId);
        System.out.println("requestCapt==" + requestCapt);

        String key = this.redisConfig.getUserKeyPrefix() + "login:capt:" + requestId;
        String code = redisTemplate.boundValueOps(key).get();
        if ("".equals(requestCapt) || requestCapt == null || !code.equalsIgnoreCase(requestCapt)) {
            PrintWriter printWriter = response1.getWriter();
            String json = JSONObject.toJSONString(Result.failed("验证码有误"));
            printWriter.println(json);
            printWriter.flush();
            printWriter.close();
            System.out.println("验证码有误");
            return;
        } else {
            PrintWriter printWriter = response1.getWriter();
            String json = JSONObject.toJSONString(Result.success("成功"));
            printWriter.println(json);
            printWriter.flush();
            printWriter.close();
            System.out.println("成功");
            chain.doFilter(request1, response1);
        }

    }
}
