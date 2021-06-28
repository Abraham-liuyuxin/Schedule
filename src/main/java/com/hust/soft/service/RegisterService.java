package com.hust.soft.service;

import com.hust.soft.mapper.UserRepository;
import com.hust.soft.model.entity.Role;
import com.hust.soft.model.entity.User;
import com.hust.soft.model.vo.RegisterUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RegisterService {
    @Getter
    private String verification_code;
    @Autowired
    UserRepository userRepository;

    static public List<Role> authorization(String name){
        Role role = new Role();
        role.setName("ROLE_" + name);
        List<Role> res = new ArrayList<>();
        res.add(role);
        return res;
    }

    public boolean emailCheck(RegisterUser registerUser){
        User user = userRepository.findUsersByEmail(registerUser.getEmail());
        return user==null;
    }
    public boolean userIntoDb(RegisterUser registerUser){
        if(emailCheck(registerUser)){
            //生成邮箱验证链接码
            String randomCode = RandomString.make(64);
            this.verification_code = randomCode;
            User user = new User(registerUser);
            user.setRoles(authorization("user"));
            user.setVerification_code(randomCode);
            userRepository.saveAndFlush(user);
            return true;
        }
        return false;
    }
    public boolean verify(String verification_code, String email){
        String toVerification_code = userRepository.findUsersByEmail(email).getVerification_code();
        return toVerification_code.equals(verification_code);
    }

    public void activate(String verification_code, String email){
        User user = userRepository.findUsersByEmail(email);
        user.setEnabled(true);
        userRepository.saveAndFlush(user);
    }
}
