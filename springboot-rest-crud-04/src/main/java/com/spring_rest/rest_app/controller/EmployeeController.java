package com.spring_rest.rest_app.controller;


import com.spring_rest.rest_app.dto.EmployeePatchDto;
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


    //GET ALL EMPLOYEES
    @GetMapping
    public ResponseEntity<Page<EmployeeResponseDto>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(employeeService.getAllEmployees(page, size));
    }


    //GET EMPLOYEE BY ID
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> getEmployeesById(@PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeesById(employeeId));
    }


    //ADD EMPLOYEES
    @PostMapping
    public ResponseEntity<EmployeeResponseDto> addEmployees(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {

        EmployeeResponseDto savedEmployee = employeeService.addEmployee(employeeRequestDto);


        return ResponseEntity.created(URI.create("/rest/employees/" + savedEmployee.id()))
                .body(savedEmployee);
    }

    //UPDATE EMPLOYEE
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> updateEmployees(@PathVariable Long employeeId, @Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDto updateEmployee = employeeService.updateEmployee(employeeId,employeeRequestDto);

        return ResponseEntity.created(URI.create("/rest/employee" + updateEmployee.id()))
                .body(updateEmployee);
    }


    //UPDATE EMPLOYEE DETAILS PARTIALLY WITH PATCH
    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> patchEmployees(
            @PathVariable Long employeeId,
            @Valid @RequestBody EmployeePatchDto employeePatchDto
    ){
        EmployeeResponseDto patchEmployee = employeeService.patchEmployee(employeeId,employeePatchDto);
        return ResponseEntity.created(URI.create("/rest/employee" + patchEmployee.id()))
                .body(patchEmployee);
    }


    //DELETE EMPLOYEE BY ID
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> deleteEmployees(@PathVariable Long employeeId) {
        employeeService.removeEmployeeById(employeeId);

        return ResponseEntity.noContent().build();
    }

}
