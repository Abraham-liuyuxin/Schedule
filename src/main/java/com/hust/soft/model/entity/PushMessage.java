package com.hust.soft.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "t_push")
@Data
public class PushMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long push_id;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private User user;

    private String push_theme;
    private String push_subject;
    private boolean push_isAccepted;
}
