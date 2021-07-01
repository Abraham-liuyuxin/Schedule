package com.hust.soft.aspect;

import com.hust.soft.mapper.TaskAnalysisRepository;
import com.hust.soft.mapper.TaskRepository;
import com.hust.soft.mapper.UserRepository;
import com.hust.soft.model.entity.TaskAnalysis;
import com.hust.soft.model.entity.User;
import com.hust.soft.utils.LocalDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
public class Schedule {
    @Autowired
    TaskAnalysisRepository taskAnalysisRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    //每天0时0分1秒对前天的用户数据进行分析
    @Scheduled(cron = "1 0 0 * ? ?")
    //@Scheduled(initialDelay = 1000, fixedRate = 10000)
    public String saveToTaskAnalysis(){
        List<User> userList = userRepository.findAll();
        //等待改为异步
        for (User user : userList){
            TaskAnalysis taskAnalysis = generateTaskAnalysis(user);
            taskAnalysisRepository.saveAndFlush(taskAnalysis);
        }
        return null;
    }

    private TaskAnalysis generateTaskAnalysis(User user){
        LocalDate date = LocalDate.now().minusDays(1);
        Date yesterday = LocalDateUtil.localDate2Date(date);

        int tasks = taskRepository.findAllByUserAndTaskCreate(user, yesterday).size();
        int finishedTasks = taskRepository.findAllByUserAndTaskIsFinishedAndTaskCreate(user, true, yesterday).size();
        float rate = (float) finishedTasks/tasks;

        TaskAnalysis taskAnalysis = new TaskAnalysis();
        taskAnalysis.setAnalysisTaskNum(tasks);
        taskAnalysis.setAnalysisFinishedNum(finishedTasks);
        taskAnalysis.setAnalysisFinishRate(tasks==0 ? 0 : rate);
        taskAnalysis.setAnalysisDay(yesterday);
        taskAnalysis.setUser(user);

        return taskAnalysis;
    }
}
