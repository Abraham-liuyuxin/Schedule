package com.hust.soft.mapper;

import com.hust.soft.model.entity.Task;
import com.hust.soft.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUser(User user);
    List<Task> findAllByUserAndTaskCreate(User user, Date date);
    List<Task> findAllByUserAndTaskIsFinishedAndTaskCreate(User user, Boolean flag, Date date);
}
