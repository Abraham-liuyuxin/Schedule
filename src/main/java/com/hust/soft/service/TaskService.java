package com.hust.soft.service;

import com.hust.soft.mapper.TaskRepository;
import com.hust.soft.mapper.UserRepository;
import com.hust.soft.model.dto.TaskDTO;
import com.hust.soft.model.entity.Task;
import com.hust.soft.model.entity.User;
import com.hust.soft.model.vo.SaveTaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    public User getUser(String userEmail) throws NullPointerException{
        User user = userRepository.findUsersByEmail(userEmail);
        return user;
    }

    public List<TaskDTO> getTaskList(User user){
        List<Task> list = taskRepository.findAllByUser(user);
        List<TaskDTO> res = list.stream().map(TaskDTO::new).collect(Collectors.toList());
        return  res;
    }

    //返回什么
    public String saveTask(SaveTaskVO taskVO, User user){
        Task task = new Task();

        //将前端数据注入
        task.setTask_subject(taskVO.getSubject());
        task.setTask_theme(taskVO.getTheme());
        task.setTask_ddl(taskVO.getDdl());
        task.setTask_priority(taskVO.getPriority());
        task.setTask_priority(taskVO.getPriority());

        task.setUser(user);
        task.setTask_isFinished(false);
        task.setTask_create(new Date());

        taskRepository.saveAndFlush(task);
        return null;
    }

}
