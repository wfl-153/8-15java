package com.oracle.application.service.impl;

import com.oracle.application.entity.Users;
import com.oracle.application.mapper.UsersMapper;
import com.oracle.application.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wfl
 * @since 2023-08-15
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
