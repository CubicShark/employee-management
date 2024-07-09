package com.example.employeemanagement.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessages {
    public static final String EMPLOYEE_NOT_FOUND_MESSAGE = "Employee with id %d not found";
    public static final String ENTITY_RESOURCE_MISSING_MESSAGE = "Entity %s is missing";
}
