package com.example.employeemanagement.entity;

import lombok.Data;

@Data
public class Employee {

    private long id;
    private String email;
    private String password;
    private String name;
    private Role role;
}
