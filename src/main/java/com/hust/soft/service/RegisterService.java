package com.hust.soft.service;

import com.hust.soft.mapper.UserRepository;
import com.hust.soft.model.entity.User;
import com.hust.soft.model.vo.RegisterUser;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RegisterService {

    @Autowired
    UserRepository userRepository;

    public boolean emailCheck(RegisterUser registerUser){
        User user = userRepository.findUsersByEmail(registerUser.getEmail());
        return user==null;
    }
    public boolean userIntoDb(RegisterUser registerUser){
        if(emailCheck(registerUser)){
            User user = new User(registerUser);
            userRepository.saveAndFlush(user);
            return true;
        }
        return false;
    }
}
