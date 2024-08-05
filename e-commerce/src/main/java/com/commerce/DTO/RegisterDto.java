package com.commerce.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class RegisterDto {


    private String  username;

    private String password;

    private String email;

    private List<String> roles;
}
