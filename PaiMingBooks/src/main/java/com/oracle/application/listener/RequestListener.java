//package com.oracle.application.listener;
//
//import lombok.extern.slf4j.Slf4j;
//
//import javax.servlet.ServletRequestEvent;
//import javax.servlet.ServletRequestListener;
//import javax.servlet.annotation.WebListener;
//
///**
// * @Author 王飞龙
// * @Date 2023/8/12 11:00
// * @Version 1.0
// */
//@WebListener
//@Slf4j
//public class RequestListener implements ServletRequestListener {
//
//    @Override
//    public void requestDestroyed(ServletRequestEvent sre) {
//        log.info("监听器被销毁了");
//        ServletRequestListener.super.requestDestroyed(sre);
//    }
//
//    @Override
//    public void requestInitialized(ServletRequestEvent sre) {
//        log.info("监听器被创建了");
//        ServletRequestListener.super.requestInitialized(sre);
//    }
//}
