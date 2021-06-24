package com.hust.soft.controller;

import com.hust.soft.model.entity.User;
import com.hust.soft.model.vo.RegisterUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello Spring Security!";
    }

    @RequestMapping("/test/register")
    public User register(@RequestBody RegisterUser registerUser){
        User user = new User();
        user.setEmail(registerUser.getEmail());
        user.setPassword(registerUser.getPassword());
        return user;
    }
}
