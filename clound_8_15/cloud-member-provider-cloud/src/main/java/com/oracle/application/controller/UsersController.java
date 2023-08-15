package com.oracle.application.controller;


import com.oracle.application.entity.Users;
import com.oracle.application.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wfl
 * @since 2023-08-15
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private IUsersService iUsersService;

    @GetMapping("/users/{id}")
    public Users get(@PathVariable("id")Integer id){
        System.out.println("iUsersService = " + id);
        Users byId = iUsersService.getById(id);
        return byId;
    }

}
