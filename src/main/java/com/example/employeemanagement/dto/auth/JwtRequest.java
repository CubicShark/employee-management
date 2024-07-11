package com.example.employeemanagement.dto.auth;

import lombok.Data;


public record JwtRequest (

    String username,
    String password
) {
}
