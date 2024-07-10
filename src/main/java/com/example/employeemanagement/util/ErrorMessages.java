package com.example.employeemanagement.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessages {
    public static final String EMPLOYEE_NOT_FOUND_MESSAGE = "Employee with id %d not found";
    public static final String ENTITY_RESOURCE_MISSING_MESSAGE = "Entity %s is missing";
    public static final String INTERNAL_ERROR_MESSAGE = "Internal error";
    public static final String ACCESS_DENIED_MESSAGE = "Access denied";
    public static final String VALIDATION_FAILED_MESSAGE = "Validation failed";
}
