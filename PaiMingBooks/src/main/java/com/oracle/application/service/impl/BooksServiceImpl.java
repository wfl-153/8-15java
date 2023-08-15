package com.oracle.application.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.oracle.application.config.RedisConfig;
import com.oracle.application.entity.Books;
import com.oracle.application.entity.Rank;
import com.oracle.application.mapper.BooksMapper;
import com.oracle.application.service.IBooksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wfl
 * @since 2023-08-11
 */
@Service
@Slf4j
public class BooksServiceImpl extends ServiceImpl<BooksMapper, Books> implements IBooksService {

    @Autowired
    private BooksMapper booksMapper;

    @Autowired
    private RedisConfig redisConfig;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void initRankData() {
        log.info("初始化开始执行");
        String lockKey = this.redisConfig.getBooksKeyPrefix() + "rank:lock";
        Boolean ifAbsent = this.redisTemplate.boundValueOps(lockKey).setIfAbsent("aaa");
        if (ifAbsent) {
            //执行
            log.info("初始化执行完毕");
            init();
            return;
        }
        log.info("初始化不能重复执行");
    }

    @Override
    public void addScore(Integer bookId) {
        String ranKKey = this.redisConfig.getBooksKeyPrefix() + "rank";
        this.redisTemplate.boundZSetOps(ranKKey).incrementScore(bookId + "", 0.1d);
    }

    @Override
    public void doRankJob() {
        //设置zset的key，存放的图书ID  value=图书的id score=1  {key value score}
        String rankKey = this.redisConfig.getBooksKeyPrefix() + "rank";
        //hash排名的实际值，存放的是rank数据  key=图书id value=图书的具体属性  {key  field value}
        String rankDataKey = this.redisConfig.getBooksKeyPrefix() + "rank:data";
        String rankData = this.redisConfig.getBooksKeyPrefix() + "rank:data:cache";
        //获取当前最新排名
        Set<String> stringIds = this.redisTemplate.boundZSetOps(rankKey).reverseRange(0, -1);
        //遍历集合，取出集合中rank对象所对应的bookid 放到list的collection集合当中
        Collection collection = stringIds.stream().map(ids -> {
            return ids;
        }).collect(Collectors.toList());
        //再将collection放到反序列化的hash中获取到为json字符串形式的Rank对象集合
        List<String> list = this.redisTemplate.boundHashOps(rankDataKey).multiGet(collection);
        //使用一个线程安全的integer
        AtomicInteger atomicInteger = new AtomicInteger();
        //将rank集合反序列化
        List<Rank> rankList = list.stream().map(s -> {
            //s就是遍历的每一个rank
            Rank rank = JSONObject.parseObject(s, Rank.class);
            //先设置旧排名 由于初始化的旧排名没有设置 我们现在要更新排名 则只需要把之前的当前排名给旧排名就可以啦
            rank.setOldNo(rank.getCurrNo());
            //重新获取当前的排名
            rank.setCurrNo(atomicInteger.incrementAndGet());
            //在rank类中定义静态比较方法
            rank.setTag(Rank.getMx(rank.getOldNo(), rank.getCurrNo()));
            //在return之前 要重新缓存一遍最新排名
            String rankJson = JSONObject.toJSONString(rank);
            this.redisTemplate.boundHashOps(rankDataKey).put(rank.getBookId() + "", rankJson);
            return rank;
        }).collect(Collectors.toList());
        String str = JSONObject.toJSONString(rankList);
        this.redisTemplate.boundValueOps(rankData).set(str);

    }

    @Override
    public List<Rank> getRankData() {
        String rankData = this.redisConfig.getBooksKeyPrefix() + "rank:data:cache";
        String s = redisTemplate.boundValueOps(rankData).get();
        List<Rank> ranks = JSONObject.parseArray(s, Rank.class);
        return ranks;
    }

    @Override
    public Books finById(Integer bookId) {
        Books book = booksMapper.selectById(bookId);
        return book;
    }


    private void init() {
//        通过mysql获取记录
        List<Books> booksList = this.booksMapper.selectList(null);
        //zset排名的类型 存放的是图书id
        String rankKey = redisConfig.getBooksKeyPrefix() + "rank";
        //hash排名的实际值，存放的是rank数据
        String rankDataKey = redisConfig.getBooksKeyPrefix() + "rank:data";
        Map<String, String> map = new HashMap<>();
        AtomicInteger atomicInteger = new AtomicInteger();
        booksList.stream().forEach(books -> {
            redisTemplate.boundZSetOps(rankKey).add(books.getId() + "", 1d);
            Rank rank = new Rank();
            rank.setBookId(books.getId());
            rank.setTitle(books.getTitle());
            rank.setCurrNo(atomicInteger.incrementAndGet());
            rank.setTag("-");
            String rankJson = JSONObject.toJSONString(rank);
            map.put(rank.getBookId() + "", rankJson);
        });
        redisTemplate.boundHashOps(rankDataKey).putAll(map);
    }


}
