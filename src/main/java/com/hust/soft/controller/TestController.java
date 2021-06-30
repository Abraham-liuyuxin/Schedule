package com.hust.soft.controller;

import com.hust.soft.model.entity.User;
import com.hust.soft.model.vo.RegisterUserVO;
import com.hust.soft.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;

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
    public User register(@RequestBody RegisterUserVO registerUserVO){
        User user = new User();
        user.setEmail(registerUserVO.getEmail());
        user.setPassword(registerUserVO.getPassword());
        return user;
    }

    @RequestMapping("/test/find")
    public User find(){
        User user = new User();
        user = userService.getUserById(Long.valueOf("6"));
        return user;
    }

    @RequestMapping("/test/hello/user")
    public String helloUser(Principal principal){
        return "当前登录用户：" + principal.getName();
    }

}
