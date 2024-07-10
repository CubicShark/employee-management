package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.auth.JwtRequest;
import com.example.employeemanagement.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);
}
