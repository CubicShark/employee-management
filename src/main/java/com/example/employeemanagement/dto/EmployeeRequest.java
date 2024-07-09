package com.example.employeemanagement.dto;

import com.example.employeemanagement.dto.validation.OnCreate;
import com.example.employeemanagement.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EmployeeRequest (

        @NotBlank(message = "{email.notblank}", groups = {OnCreate.class, OnUpdate.class})
        @Length(max = 32, message = "{email.length}", groups = {OnCreate.class, OnUpdate.class})
        String email,

        @NotBlank(message = "{password.notblank}", groups = {OnCreate.class, OnUpdate.class})
        @Length(max = 32, message = "{password.length}", groups = {OnCreate.class, OnUpdate.class})
        String password,

        @NotBlank(message = "{name.notblank}", groups = {OnCreate.class, OnUpdate.class})
        @Length(max = 32, message = "{name.length}", groups = {OnCreate.class, OnUpdate.class})
        String name
) {
}
