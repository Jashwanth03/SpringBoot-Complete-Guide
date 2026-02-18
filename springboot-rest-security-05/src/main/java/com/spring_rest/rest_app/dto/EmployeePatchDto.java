package com.spring_rest.rest_app.dto;

public record EmployeePatchDto(
        String firstName,
        String lastName,
        String email
) {
}
