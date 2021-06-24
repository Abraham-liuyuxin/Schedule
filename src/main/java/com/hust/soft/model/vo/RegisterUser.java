package com.hust.soft.model.vo;

import com.hust.soft.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUser{
    private String email;
    private String password;
}
