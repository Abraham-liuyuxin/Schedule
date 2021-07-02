package com.hust.soft.service;

import com.hust.soft.mapper.UserInfoRepository;
import com.hust.soft.mapper.UserRepository;
import com.hust.soft.model.entity.User;
import com.hust.soft.model.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    public User getUserByEmail(String email){
        User user = userRepository.findUsersByEmail(email);
        return user;
    }

    public String saveUserWithInfo(User user, UserInfo userInfo){
        Long oldUserInfoId = user.getUserInfo().getUserInfoId();
        user.setUserInfo(userInfo);
        userRepository.saveAndFlush(user);
        userInfoRepository.deleteById(oldUserInfoId);
        userInfoRepository.flush();
        return "保存成功";
    }

}
