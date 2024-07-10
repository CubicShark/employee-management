package com.example.employeemanagement.exception;

import lombok.Builder;

import java.util.Map;

@Builder
public record ExceptionBody (
        Map<String, String> errors,
        Integer status,
        String message
) {
}
