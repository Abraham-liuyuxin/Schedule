package com.hust.soft.model;

import lombok.Data;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import javax.persistence.*;

@Entity(name = "t_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//User为维护端，删除User，级联删除UserInfo
    @JoinTable(name = "user_userinfo",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "userInfo_id"))
    private UserInfo userInfo;

}
