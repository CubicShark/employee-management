package com.example.employeemanagement.dto;

import com.example.employeemanagement.dto.validation.OnCreate;
import com.example.employeemanagement.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EmployeeDto(

        @NotBlank(message = "{email.notblank}", groups = {OnCreate.class, OnUpdate.class})
        @Length(max = 32, message = "{email.length}", groups = {OnCreate.class, OnUpdate.class})
        @Email
        String email,

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotBlank(message = "{password.notblank}", groups = {OnCreate.class, OnUpdate.class})
        @Length(max = 32, message = "{password.length}", groups = {OnCreate.class, OnUpdate.class})
        String password,

        @NotBlank(message = "{name.notblank}", groups = {OnCreate.class, OnUpdate.class})
        @Length(max = 32, message = "{name.length}", groups = {OnCreate.class, OnUpdate.class})
        String name
) {
}
