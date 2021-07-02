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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class TaskAnalysisController {
    @Resource
    TaskAnalysisService taskAnalysisService;
    @Resource
    UserService userService;


    @RequestMapping("/task-analysis")
    public Map<String, Object> getTaskAnalysis(){
        int countTask, countFinish;
        Map<String, Object> res = new HashMap<>();

        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByEmail(principal);
        Long userId = user.getId();

        countTask = taskAnalysisService.getCountTask(user);
        countFinish = taskAnalysisService.getCountFinish(user);

        Date date = new Date();
        List<TaskAnalysisDTO> analysis = taskAnalysisService.getTaskAnalysisLastSevenDays(user, date);

        res.put("userId", userId);
        res.put("countTask", countTask);
        res.put("countFinish", countFinish);
        res.put("analysis", analysis);

        return res;
    }
}
