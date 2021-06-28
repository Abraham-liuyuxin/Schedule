package com.hust.soft.mapper;

import com.hust.soft.model.entity.Role;
import com.hust.soft.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Test
    void findUsersByEmail() {
    }

    @Test
    void findUsersById() {
        //User user = userRepository.findUsersById(Long.valueOf("6"));
        User user = userRepository.findUsersByEmail("zhacaiji@163.com");
        List<Role> roleList = user.getRoles();
        if(roleList!=null && roleList.size()>0)
        {
            System.out.print("用户拥有的权限：");
            for (Role role : roleList)
            {
                System.out.print(role.getName()+";");
            }
        }
        System.out.println("\n-----------------------------------------------");
    }
}