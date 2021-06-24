package com.hust.soft.controller;

import com.hust.soft.model.entity.User;
import com.hust.soft.model.vo.RegisterUser;
import com.hust.soft.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RegisterController {
    @Resource
    RegisterService registerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/register")
    public Map register(@RequestBody RegisterUser registerUser){
        //加密密码
        registerUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        Boolean dataIntoDb = registerService.userIntoDb(registerUser);
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("data", dataIntoDb);
        return map;
    }

}
