package com.example.employeemanagement.dto.auth;

import lombok.Data;

@Data
public class JwtRequest {

    private String username;

    private String password;
}
