package com.spring_rest.rest_app.dto;

public record EmployeeRequestDto(
        String firstName,
        String lastName,
        String email
) {
}
