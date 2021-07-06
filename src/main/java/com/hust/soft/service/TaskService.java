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
import java.util.Optional;
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

    public List<TaskDTO> getUnfinishedTaskList(User user){
        List<Task> list = taskRepository.findAllByUserAndTaskIsFinished(user, false);
        List<TaskDTO> res = list.stream().map(TaskDTO::new).collect(Collectors.toList());
        return  res;
    }

    //返回什么
    public String saveTask(SaveTaskVO taskVO, User user){
        Task task = new Task();
        //将前端数据注入
        task.setTaskSubject(taskVO.getSubject());
        task.setTaskTheme(taskVO.getTheme());
        task.setTaskDdl(taskVO.getDdl());
        task.setTaskPriority(taskVO.getPriority());
        task.setTaskRemind(taskVO.getRemind());

        task.setUser(user);
        task.setTaskIsFinished(false);
        task.setTaskCreate(new Date());

        taskRepository.saveAndFlush(task);
        return null;
    }

    public String updateTask(TaskDTO taskDTO, User user){

        Task task = taskRepository.getById(taskDTO.getTaskId());
        if(!user.equals(task.getUser())) return "修改失败：该用户无权限";

        //将前端数据注入
        task.setTaskSubject(taskDTO.getSubject());
        task.setTaskTheme(taskDTO.getTheme());
        task.setTaskDdl(taskDTO.getDdl());
        task.setTaskPriority(taskDTO.getPriority());
        task.setTaskRemind(taskDTO.getRemind());

        task.setTaskCreate(new Date());
        taskRepository.saveAndFlush(task);
        return "修改成功";
    }


    public String finishTask(Long taskId, User user){
        String s = "修改失败";
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isPresent() && user.getId()==task.get().getUser().getId()){
            task.get().setTaskIsFinished(true);
            taskRepository.saveAndFlush(task.get());
            s = "修改成功";
        }
        return s;
    }

    public String deleteTask(Long taskId, User user){
        String s = "删除失败";
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isPresent() && user.getId()==task.get().getUser().getId()){
            task.get().setTaskIsFinished(true);
            taskRepository.delete(task.get());
            s = "删除成功";
        }
        return s;
    }

}