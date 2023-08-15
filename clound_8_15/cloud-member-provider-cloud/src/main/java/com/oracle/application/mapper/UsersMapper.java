package com.oracle.application.mapper;

import com.oracle.application.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wfl
 * @since 2023-08-15
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

}
