package com.oracle.application.mapper;

import com.oracle.application.entity.Books;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wfl
 * @since 2023-08-11
 */
@Mapper
public interface BooksMapper extends BaseMapper<Books> {

    //用于初始化数据操作
    void initRankData();


}
