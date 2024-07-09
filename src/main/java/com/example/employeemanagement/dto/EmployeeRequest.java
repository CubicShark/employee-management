package com.example.employeemanagement.dto;

import com.example.employeemanagement.dto.validation.OnCreate;
import com.example.employeemanagement.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record EmployeeRequest (

        @NotNull(message = "{email.notnull}", groups = {OnCreate.class, OnUpdate.class})
        @Length(max = 32, message = "{email.length}", groups = {OnCreate.class, OnUpdate.class})
        String email,

        @NotNull(message = "{password.notnull}", groups = {OnCreate.class, OnUpdate.class})
        @Length(max = 32, message = "{password.length}", groups = {OnCreate.class, OnUpdate.class})
        String password,

        @NotNull(message = "{name.notnull}", groups = {OnCreate.class, OnUpdate.class})
        @Length(max = 32, message = "{name.length}", groups = {OnCreate.class, OnUpdate.class})
        String name
) {
}
