//package com.oracle.application.config;
//
//import com.oracle.application.filter.MyFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//
///**
// * @Author 王飞龙
// * @Date 2023/8/12 10:46
// * @Version 1.0
// */
//@Configuration
//public class FilterConfig {
//
//    @Bean
//    public FilterRegistrationBean crosFilter() {
//        FilterRegistrationBean<MyFilter> filterFilterRegistrationBean=new FilterRegistrationBean<MyFilter>();
//        filterFilterRegistrationBean.setFilter(new MyFilter());
//        filterFilterRegistrationBean.addUrlPatterns("/books/getbook");
//        return filterFilterRegistrationBean;
//    }
//}
