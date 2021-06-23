package com.hust.soft.service;


import com.hust.soft.mapper.UserRepository;
import com.hust.soft.model.dto.MyUserDetails;
import com.hust.soft.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUsersByEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        //暂时——将数据库里的明文密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new MyUserDetails(user);
    }
}
