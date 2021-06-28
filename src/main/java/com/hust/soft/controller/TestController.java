package com.hust.soft.controller;

import com.hust.soft.model.entity.User;
import com.hust.soft.model.vo.RegisterUser;
import com.hust.soft.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    UserService userService;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello Spring Security!";
    }

    @RequestMapping("/user/hello")
    public String helloUser(){
        return "User: Hello Spring Security!";
    }

    @RequestMapping("/admin/hello")
    public String helloAdmin(){
        return "Admin: Hello Spring Security!";
    }

    @RequestMapping("/test/register")
    public User register(@RequestBody RegisterUser registerUser){
        User user = new User();
        user.setEmail(registerUser.getEmail());
        user.setPassword(registerUser.getPassword());
        return user;
    }

    @RequestMapping("/test/find")
    public User find(){
        User user = new User();
        user = userService.getUserById(Long.valueOf("6"));
        return user;
    }
}
