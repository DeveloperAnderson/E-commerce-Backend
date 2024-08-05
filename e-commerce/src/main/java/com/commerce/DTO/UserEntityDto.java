package com.commerce.DTO;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.pets.entity.UserEntity}
 */
@Value
public class UserEntityDto implements Serializable {
    String username;
    private String email;
    Boolean locked;
    Boolean disabled;
    List<String> roles;
}