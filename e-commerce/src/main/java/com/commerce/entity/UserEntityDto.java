package com.commerce.entity;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link UserEntity}
 */
@Value
public class UserEntityDto implements Serializable {
    String username;
    String email;
}