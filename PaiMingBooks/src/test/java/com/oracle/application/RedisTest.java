package com.oracle.application;

import com.oracle.application.entity.Books;
import com.oracle.application.entity.Rank;
import com.oracle.application.service.IBooksService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author 王飞龙
 * @Date 2023/8/11 14:56
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {

    @Autowired
    private IBooksService iBooksService;

    @Test
    public void initData(){
        iBooksService.initRankData();
    }

    @Test
    public void add(){
        iBooksService.addScore(1);
    }

    @Test
    public void px(){
        iBooksService.doRankJob();
    }

    @Test
    public void getData(){
        List<Rank> rankData = iBooksService.getRankData();
        rankData.stream().forEach(System.out::println);
    }




}
