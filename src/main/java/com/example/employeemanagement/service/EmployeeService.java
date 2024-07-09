package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.EmployeeRequest;
import com.example.employeemanagement.dto.EmployeeResponse;

public interface EmployeeService {

    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);
}
