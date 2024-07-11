package com.example.employeemanagement.dto.auth;

public record JwtResponse (

    Long id,
    String username,
    String accessToken,
    String refreshToken
) {
}
