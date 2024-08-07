package com.commerce.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateDto {


    private String  username;
    private String email;
    private Boolean locked;
    private Boolean disabled;
    private List<String> roles;
}
