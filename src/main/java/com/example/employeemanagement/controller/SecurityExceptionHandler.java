package com.example.employeemanagement.controller;

import com.example.employeemanagement.exception.ExceptionBody;
import com.example.employeemanagement.util.ErrorMessages;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionBody handleAccessDenied() {
        return ExceptionBody.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message(ErrorMessages.ACCESS_DENIED_MESSAGE)
                .build();
    }
}
