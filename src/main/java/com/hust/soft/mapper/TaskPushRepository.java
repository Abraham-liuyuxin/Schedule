package com.hust.soft.mapper;

import com.hust.soft.model.entity.PushMessage;
import com.hust.soft.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskPushRepository extends JpaRepository<PushMessage, Long> {

    PushMessage findFirstByPushSubject(String subject);
    PushMessage findFirstByUser(User user);
}
