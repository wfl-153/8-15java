package com.oracle.application.service;

import com.oracle.application.entity.Books;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oracle.application.entity.Rank;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wfl
 * @since 2023-08-11
 */
public interface IBooksService extends IService<Books> {
    /**
     * 初始化的方法
     */
    void initRankData();


    /**
     * 根据图书id加分值的方法(改变排名的方法)
     *
     * @param bookId
     */
    void addScore(Integer bookId);

    /**
     * 获取排名结果(查询排名的方法)
     *
     * @return
     */
    void doRankJob();


    List<Rank> getRankData();


    Books finById(Integer bookId);
}
