package com.oracle.application.controller;

import com.oracle.application.feign.UserFeign;
import com.oracle.application.feign.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 王飞龙
 * @Date 2023/8/15 14:54
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserFeign userFeign;


    @GetMapping("/users/{id}")
    public Users get(@PathVariable("id")Integer id){
        System.out.println("iUsersService = " + id);
        Users byId = userFeign.get(id);
        return byId;
    }


}
