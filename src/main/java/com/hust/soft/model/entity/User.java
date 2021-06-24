package com.hust.soft.model.entity;

import com.hust.soft.model.vo.RegisterUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "t_user")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String email;
    private String password;

    private boolean enabled;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//User为维护端，删除User，级联删除UserInfo
    @JoinTable(name = "user_userinfo",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "userInfo_id"))
    private UserInfo userInfo;

    public User(RegisterUser registerUser){
        this.email = registerUser.getEmail();
        this.password = registerUser.getPassword();
    }

}
