package com.hust.soft.mapper;

import com.hust.soft.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findUsersByEmail(String email);
    User findUsersById(Long id);
}


