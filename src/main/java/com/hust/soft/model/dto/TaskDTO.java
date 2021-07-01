package com.hust.soft.model.dto;

import com.hust.soft.model.entity.Task;
import com.hust.soft.model.vo.SaveTaskVO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TaskDTO {
    private String subject;
    private String theme;
    private Date ddl;
    private Date createAt;
    private int priority;
    private int remind;
    private boolean isFinished;

    private Long userId;
    private Long taskId;

    public TaskDTO(Task task){
        this.createAt = task.getTaskCreate();
        this.ddl = task.getTaskDdl();
        this.isFinished = task.isTaskIsFinished();
        this.priority = task.getTaskPriority();
        this.remind = task.getTaskRemind();
        this.subject = task.getTaskSubject();
        this.theme = task.getTaskTheme();

        this.userId = task.getUser().getId();
        this.taskId = task.getTaskId();
    }

    public TaskDTO(SaveTaskVO taskVO){
        this.subject = taskVO.getSubject();
        this.remind = taskVO.getRemind();
        this.ddl = taskVO.getDdl();
        this.theme = taskVO.getTheme();
        this.priority= taskVO.getPriority();
    }
}
