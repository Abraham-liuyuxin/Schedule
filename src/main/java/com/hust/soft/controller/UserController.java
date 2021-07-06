package com.hust.soft.controller;

import com.hust.soft.model.dto.TaskDTO;
import com.hust.soft.model.entity.User;
import com.hust.soft.model.vo.SaveTaskVO;
import com.hust.soft.service.TaskService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    TaskService taskService;

    @RequestMapping("/task-list")
    public List<TaskDTO>  getTaskList(){
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = taskService.getUser(principal);
        List<TaskDTO> res = taskService.getTaskList(user);
        return res;
    }

    @RequestMapping("/save-task")
    public String saveTask(@RequestBody SaveTaskVO taskVO){
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = taskService.getUser(principal);
        taskService.saveTask(taskVO, user);
        return principal + "：保存任务成功";
    }

    @RequestMapping("/finish-task")
    public String finishTask(@RequestParam("taskId") Long taskId){
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = taskService.getUser(principal);
        String res = taskService.finishTask(taskId, user);
        return res;
    }

    @RequestMapping("/update-task")
    public String updateTask(@RequestBody TaskDTO taskDTO){
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = taskService.getUser(principal);
        String res = taskService.updateTask(taskDTO, user);
        return res;
    }

    @RequestMapping("/delete-task")
    public String deleteTask(@RequestParam("taskId") Long taskId){
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = taskService.getUser(principal);
        String res = taskService.deleteTask(taskId, user);
        return res;
    }
}
