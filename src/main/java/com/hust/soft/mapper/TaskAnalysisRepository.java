package com.hust.soft.mapper;

import com.hust.soft.model.dto.TaskAnalysisDTO;
import com.hust.soft.model.entity.TaskAnalysis;
import com.hust.soft.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Date;
import java.util.List;

public interface TaskAnalysisRepository extends JpaRepository<TaskAnalysis, Long> {
    List<TaskAnalysis> findByUserAndAnalysisDayAfter(User user, Date date);

}
