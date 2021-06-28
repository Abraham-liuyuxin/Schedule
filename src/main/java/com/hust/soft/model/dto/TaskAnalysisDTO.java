package com.hust.soft.model.dto;

import com.hust.soft.model.entity.TaskAnalysis;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TaskAnalysisDTO {
    private Date analysisDay;
    private int analysisTaskNum;
    private int analysisFinishedNum;
    private float analysisFinishRate;

    public TaskAnalysisDTO(TaskAnalysis taskAnalysis){
        this.analysisDay = taskAnalysis.getAnalysisDay();
        this.analysisFinishedNum = taskAnalysis.getAnalysisFinishedNum();
        this.analysisFinishRate = taskAnalysis.getAnalysisFinishRate();
        this.analysisTaskNum = taskAnalysis.getAnalysisTaskNum();
    }
}
