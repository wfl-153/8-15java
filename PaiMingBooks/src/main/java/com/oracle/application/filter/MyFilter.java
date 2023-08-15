//package com.oracle.application.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.ComponentScan;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @Author 王飞龙
// * @Date 2023/8/12 10:45
// * @Version 1.0
// */
//@ComponentScan
//@Slf4j
//public class MyFilter implements Filter {
//
//    //重写其中的doFilter方法
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        log.info("过滤器执行了");
//
//        chain.doFilter(req, res);
//    }
//}