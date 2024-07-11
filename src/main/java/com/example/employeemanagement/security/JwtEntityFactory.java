package com.example.employeemanagement.security;

import com.example.employeemanagement.entity.Employee;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class JwtEntityFactory {

    public static JwtEntity create(Employee employee){
        return new JwtEntity(
                employee.getId(),
                employee.getEmail(),
                employee.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(employee.getRole().getName()))
        );
    }
}
