package com.hust.soft.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "t_push")
@Data
public class PushMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pushId;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private User user;

    private String pushTheme;
    private String pushSubject;
    private boolean pushIsAccepted;

}
