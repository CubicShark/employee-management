package com.example.employeemanagement.exception;

import com.example.employeemanagement.util.ErrorMessages;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(Long id) {
        super(String.format(ErrorMessages.EMPLOYEE_NOT_FOUND_MESSAGE, id));
    }

    public EntityNotFoundException(String entityName) {
        super((String.format(ErrorMessages.ENTITY_RESOURCE_MISSING_MESSAGE, entityName)));
    }
}
