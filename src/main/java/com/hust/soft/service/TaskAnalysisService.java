package com.hust.soft.service;

import com.hust.soft.mapper.TaskAnalysisRepository;
import com.hust.soft.mapper.TaskRepository;
import com.hust.soft.mapper.UserRepository;
import com.hust.soft.model.dto.TaskAnalysisDTO;
import com.hust.soft.model.entity.Task;
import com.hust.soft.model.entity.TaskAnalysis;
import com.hust.soft.model.entity.User;
import com.hust.soft.utils.LocalDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskAnalysisService {
    @Autowired
    TaskAnalysisRepository taskAnalysisRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    Random random = new Random(624);
    public void saveTest(TaskAnalysis taskAnalysis, User user){
        int analysis_task_num;
        int analysis_finished_num;
        float analysis_finish_rate;

        analysis_finished_num = random.nextInt(100);
        analysis_task_num = random.nextInt(100)+analysis_finished_num;
        analysis_finish_rate = (float) analysis_finished_num/analysis_task_num;
        Date date = new Date();

        taskAnalysis.setAnalysisDay(date);
        taskAnalysis.setAnalysisTaskNum(analysis_task_num);
        taskAnalysis.setAnalysisFinishedNum(analysis_finished_num);
        taskAnalysis.setAnalysisFinishRate(analysis_finish_rate);
        taskAnalysis.setUser(user);

        taskAnalysisRepository.saveAndFlush(taskAnalysis);
    }
    public String saveTaskAnalysis(TaskAnalysis taskAnalysis, User user, Date date){
        int tasks = taskRepository.findAllByUser(user).size();


        taskAnalysis.setUser(user);
        taskAnalysisRepository.saveAndFlush(taskAnalysis);
        return null;
    }





    public User getUser(String userEmail){
        User user = userRepository.findUsersByEmail(userEmail);
        return user;
    }

    public List<TaskAnalysisDTO> getTaskAnalysisLastSevenDays(User user, Date date){

        LocalDateTime now = LocalDateUtil.date2LocalDateTime(date);
        //获取过去7天的分析
        LocalDateTime then = now.minusDays(7);
        date = LocalDateUtil.localDateTime2Date(then);
        List<TaskAnalysis> list = taskAnalysisRepository.findByUserAndAnalysisDayAfter(user, date);

        //将TaskAnalysis转换为TaskAnalysisDTO并排序
        List<TaskAnalysisDTO> dtoList = list.stream()
                .map(TaskAnalysisDTO::new)
                .sorted(Comparator.comparing(TaskAnalysisDTO::getAnalysisDay))
                .collect(Collectors.toList());

        return dtoList;
    }


}
