package com.hust.soft.service;


import com.hust.soft.mapper.TaskPushRepository;
import com.hust.soft.model.dto.TaskDTO;
import com.hust.soft.model.entity.PushMessage;
import com.hust.soft.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskPushService {

    @Autowired
    TaskPushRepository taskPushRepository;

    public String saveTaskPush(TaskDTO taskDTO, User user){
        PushMessage pushMessage = new PushMessage();
        pushMessage.setPushSubject(taskDTO.getSubject());
        pushMessage.setPushTheme(taskDTO.getTheme());
        pushMessage.setUser(user);

        taskPushRepository.saveAndFlush(pushMessage);

        return null;
    }


    public PushMessage getPush(User user){
        PushMessage pushMessage1 = taskPushRepository.findFirstByUser(user);
        String sub = pushMessage1.getPushTheme();
        PushMessage pushMessage2 = taskPushRepository.findFirstByPushSubject(sub);
        return pushMessage2;
    }

}
