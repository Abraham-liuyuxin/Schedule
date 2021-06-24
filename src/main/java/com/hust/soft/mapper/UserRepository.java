package com.hust.soft.mapper;

import com.hust.soft.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUsersByEmail(String email);


}


