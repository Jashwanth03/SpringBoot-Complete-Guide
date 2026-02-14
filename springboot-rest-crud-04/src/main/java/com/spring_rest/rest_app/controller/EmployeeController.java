package com.spring_rest.rest_app.controller;


import com.spring_rest.rest_app.dto.EmployeeRequestDto;
import com.spring_rest.rest_app.dto.EmployeeResponseDto;


import com.spring_rest.rest_app.service.EmployeeService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeResponseDto>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(employeeService.getAllEmployees(page, size));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> getEmployeesById(@PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeesById(employeeId));
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> addEmployees(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {

        EmployeeResponseDto savedEmployee = employeeService.addEmployee(employeeRequestDto);


        return ResponseEntity.created(URI.create("/rest/employees/" + savedEmployee.id()))
                .body(savedEmployee);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> deleteEmployees(@PathVariable Long employeeId) {
        employeeService.removeEmployeeById(employeeId);

        return ResponseEntity.noContent().build();
    }

}
