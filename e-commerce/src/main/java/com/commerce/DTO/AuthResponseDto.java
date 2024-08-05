package com.commerce.DTO;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "status", "jwt"})
public record AuthResponseDto(String username, String message, String jwt, Boolean status) {

    public AuthResponseDto(String username, String message, String jwt, Boolean status) {
        this.username = username;
        this.message = message;
        this.jwt = jwt;
        this.status = status;
    }

    public String username() {
        return this.username;
    }

    public String message() {
        return this.message;
    }

    public String jwt() {
        return this.jwt;
    }

    public Boolean status() {
        return this.status;
    }
}
