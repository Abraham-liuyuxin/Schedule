package com.hust.soft.controller;

import com.hust.soft.model.dto.TaskDTO;
import com.hust.soft.model.entity.PushMessage;
import com.hust.soft.model.entity.User;
import com.hust.soft.service.TaskPushService;
import com.hust.soft.service.TaskService;
import com.hust.soft.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class TaskPushController {

    @Resource
    TaskPushService taskPushService;
    @Resource
    TaskService taskService;
    @Resource
    UserService userService;

    @RequestMapping("/task-push")
    public PushMessage pushMessage(){
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = taskService.getUser(principal);
        PushMessage pushMessage = taskPushService.getPush(user);
        return pushMessage;
    }

    @RequestMapping("/task/save")
    public String saveMessage(){
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = taskService.getUser(principal);
        return null;
    }


}
