package com.spring_rest.rest_app.dto;

public record EmployeeResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email

) {
}
