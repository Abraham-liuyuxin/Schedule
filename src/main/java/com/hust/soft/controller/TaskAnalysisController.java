package com.hust.soft.controller;


import com.hust.soft.model.dto.TaskAnalysisDTO;
import com.hust.soft.model.entity.TaskAnalysis;
import com.hust.soft.model.entity.User;
import com.hust.soft.service.TaskAnalysisService;
import com.hust.soft.service.UserService;
import jdk.internal.org.objectweb.asm.tree.analysis.Analyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
public class TaskAnalysisController {
    @Resource
    TaskAnalysisService taskAnalysisService;
    @Resource
    UserService userService;


    @RequestMapping("/test/save_task_analysis")
    public String saveTaskAnalysisTest(@RequestParam(value = "user_id") String userId){
        User user = userService.getUserById(Long.valueOf(userId));
        taskAnalysisService.saveTest(new TaskAnalysis(), user);
        return "保存任务分析成功";
    }

    @RequestMapping("/test/get_task_analysis")
    public List<TaskAnalysisDTO> getTaskAnalysis(@RequestParam(value = "user_id") String userId){

        User user = userService.getUserById(Long.valueOf(userId));
        Date date = new Date();
        List<TaskAnalysisDTO> res = taskAnalysisService.getTaskAnalysisLastSevenDays(user, date);
        return res;
    }
}
