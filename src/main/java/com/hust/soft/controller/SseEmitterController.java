package com.hust.soft.controller;


import com.hust.soft.model.dto.TaskDTO;
import com.hust.soft.model.entity.User;
import com.hust.soft.service.TaskService;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;


@RestController
@RequestMapping("/test/sse")
public class SseEmitterController {

    @Resource
    TaskService taskService;

    @RequestMapping("/remind")
    public SseEmitter sendMessageByTaskId(@RequestParam("taskId") Long taskId){
        TaskDTO task = null;
        final SseEmitter emitter = new SseEmitter();
        ExecutorService service = Executors.newSingleThreadExecutor();

        List<TaskDTO> taskDTOS = getUnfinishedTasks();
        for (TaskDTO taskDTO: taskDTOS){
            if (taskDTO.getTaskId().equals(taskId)){
                task = taskDTO;
            }
        }

        Long sleepTime = 0L;
        if(task!=null){
            switch (task.getRemind()){
                case 1:
                    sleepTime = 2_000L;
                    break;
                case 2:
                    sleepTime = 3_000L;
                    break;
                case 3:
                    sleepTime = 3_000L;
                    break;
                case 4:
                    sleepTime = 4_000L;
                    break;
                case 5:
                    sleepTime = 5_000L;
                    break;
            }
        }

        TaskDTO taskDTO = task;
        Map<String, Object> map = new HashMap<>();
        map.put("subject", taskDTO.getSubject());
        map.put("theme", taskDTO.getTheme());
        map.put("ddl", taskDTO.getDdl());
        map.put("priority", taskDTO.getPriority());

        try {
            emitter.send(map , MediaType.APPLICATION_JSON);
        }catch (IOException e){
            e.printStackTrace();
            emitter.completeWithError(e);
        }
        emitter.complete();

        return emitter;
    }

    @RequestMapping("/get")
    public SseEmitter sendMessage(@RequestParam("taskId") Long taskId){
        final SseEmitter emitter = new SseEmitter();
        ExecutorService service = Executors.newSingleThreadExecutor();
        List<TaskDTO> taskDTOS = getUnfinishedTasks();
        service.execute(()->{
            for (TaskDTO taskDTO : taskDTOS){
                try {
                    switch (taskDTO.getRemind()){
                        case 0:
                             Long sleepTime = 1_000L;
                             break;
                    }
                    Map<String, Object> map = new HashMap<>();
                    map.put("subject", taskDTO.getSubject());
                    map.put("theme", taskDTO.getTheme());
                    map.put("ddl", taskDTO.getDdl());
                    map.put("priority", taskDTO.getPriority());
                    emitter.send(map , MediaType.APPLICATION_JSON);
                }catch (Exception e){
                    e.printStackTrace();
                    emitter.completeWithError(e);
                    return;
                }
            }
            emitter.complete();
        });

        return emitter;
    }

    public List<TaskDTO> getUnfinishedTasks(){
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = taskService.getUser(principal);
        List<TaskDTO> taskDTOS = taskService.getUnfinishedTaskList(user);
        return taskDTOS;
    }

    @RequestMapping("/count")
    public SseEmitter handleRequest () {
        final SseEmitter emitter = new SseEmitter();
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Map<String, String> map = new HashMap<>();
                    map.put("time", LocalTime.now().toString());
                    emitter.send(map , MediaType.APPLICATION_JSON);
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                    emitter.completeWithError(e);
                    return;
                }
            }
            emitter.complete();
        });

        return emitter;
    }


}