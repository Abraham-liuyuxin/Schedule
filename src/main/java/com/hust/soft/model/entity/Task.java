package com.hust.soft.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.util.Date;

@Entity(name = "t_task")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long task_id;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private User user;

    private String task_theme;
    private String task_subject;

    @Temporal(TemporalType.DATE)//生成yyyy-MM-dd类型的日期
    //出参时间格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    //入参时，请求报文只需要传入yyyymmddhhmmss字符串进来，则自动转换为Date类型数据
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date task_ddl;

    private int task_priority;
    private int task_remind;
    private boolean task_isFinished;

    @Temporal(TemporalType.DATE)//生成yyyy-MM-dd类型的日期
    //出参时间格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    //入参时，请求报文只需要传入yyyymmddhhmmss字符串进来，则自动转换为Date类型数据
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date task_create;

}
