package com.hust.soft.controller;


import com.hust.soft.model.dto.TaskAnalysisDTO;
import com.hust.soft.model.entity.TaskAnalysis;
import com.hust.soft.model.entity.User;
import com.hust.soft.service.TaskAnalysisService;
import com.hust.soft.service.UserService;
import jdk.internal.org.objectweb.asm.tree.analysis.Analyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class TaskAnalysisController {
    @Resource
    TaskAnalysisService taskAnalysisService;
    @Resource
    UserService userService;


    public String saveTaskAnalysisTest(){
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByEmail(principal);
        taskAnalysisService.saveTest(new TaskAnalysis(), user);
        return "保存任务分析成功";
    }

    @RequestMapping("/task-analysis")
    public List<TaskAnalysisDTO> getTaskAnalysis(){
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByEmail(principal);
        Date date = new Date();
        List<TaskAnalysisDTO> res = taskAnalysisService.getTaskAnalysisLastSevenDays(user, date);
        return res;
    }
}
