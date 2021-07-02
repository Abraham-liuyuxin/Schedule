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

    final int ANALYSIS_DAY = 12;

    public List<TaskAnalysisDTO> getTaskAnalysisLastSevenDays(User user, Date date){

        LocalDateTime now = LocalDateUtil.date2LocalDateTime(date);
        //获取过去ANALYSIS_DAY天的分析
        LocalDateTime then = now.minusDays(ANALYSIS_DAY);
        date = LocalDateUtil.localDateTime2Date(then);
        List<TaskAnalysis> list = taskAnalysisRepository.findByUserAndAnalysisDayAfter(user, date);

        //将TaskAnalysis转换为TaskAnalysisDTO并排序
        List<TaskAnalysisDTO> dtoList = list.stream()
                .map(TaskAnalysisDTO::new)
                .sorted(Comparator.comparing(TaskAnalysisDTO::getAnalysisDay))
                .collect(Collectors.toList());

        return dtoList;
    }

    public int getCountFinish(User user){
        return taskAnalysisRepository.findAllByUser(user).stream().mapToInt(TaskAnalysis::getAnalysisFinishedNum).sum();
    }
    public int getCountTask(User user){
        return taskAnalysisRepository.findAllByUser(user).stream().mapToInt(TaskAnalysis::getAnalysisTaskNum).sum();
    }

}
