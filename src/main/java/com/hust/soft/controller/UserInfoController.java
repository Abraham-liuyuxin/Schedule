package com.hust.soft.controller;

import com.hust.soft.model.entity.User;
import com.hust.soft.model.entity.UserInfo;
import com.hust.soft.service.UserInfoService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Resource
    UserInfoService userInfoService;

    @RequestMapping("/userInfo/save")
    public String saveUserWithInfo(@RequestBody UserInfo userInfo){
        String s = "保存失败";
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userInfoService.getUserByEmail(principal);
        s = userInfoService.saveUserWithInfo(user, userInfo);
        return s;
    }

    @RequestMapping("/userInfo")
    public Map<String, Object> getUserInfo(){
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userInfoService.getUserByEmail(principal);
        Long userId = user.getId();
        UserInfo userInfo = user.getUserInfo();

        Map<String, Object> res = new HashMap<>();
        res.put("email", principal);
        res.put("userId", userId);
        res.put("nickname", userInfo.getNickname());
        res.put("sex", userInfo.getSex());
        res.put("age", userInfo.getAge());
        res.put("avatar", userInfo.getAvatarUrl());
        return res;
    }

}
