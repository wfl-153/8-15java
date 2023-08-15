package com.oracle.application.config;

import com.oracle.application.service.IBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Author 王飞龙
 * @Date 2023/8/14 15:41
 * @Version 1.0
 */
@Configuration
public class SetTime {

    @Autowired
    private IBooksService iBooksService;



    @Scheduled(cron ="0 * * * * *")
    public void getpx(){
        System.out.println("定时任务开始");
//        iBooksService.doRankJob();
        System.out.println("定时任务结束");
    }

}
