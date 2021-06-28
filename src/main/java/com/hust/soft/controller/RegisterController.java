package com.hust.soft.controller;

import com.hust.soft.model.entity.User;
import com.hust.soft.model.vo.RegisterUser;
import com.hust.soft.service.MailService;
import com.hust.soft.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RegisterController {
    @Resource
    RegisterService registerService;
    @Resource
    MailService mailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterUser registerUser){
        //加密密码
        registerUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        Boolean dataIntoDb = registerService.userIntoDb(registerUser);
        ResponseEntity<String> res;
        if(dataIntoDb){
            res =  new ResponseEntity<>("注册成功", HttpStatus.OK);
        }else{
            res =  new ResponseEntity<>("注册失败", HttpStatus.OK);
        }
        //发送验证邮件
        mailService.simpleMail(registerUser.getEmail(), registerService.getVerification_code());
        return res;
    }


    @RequestMapping("/register/verification")
    public String activate(@RequestParam String verification_code, @RequestParam String email){
        System.out.println("开始验证");
        String s = "激活失败";
        Boolean verification = registerService.verify(verification_code, email);
        if (verification){
            registerService.activate(verification_code, email);
            s = "激活成功";
        }
        return s;
    }

}
