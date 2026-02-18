package com.spring_rest.rest_app.service;


import com.spring_rest.rest_app.dto.EmployeePatchDto;
import com.spring_rest.rest_app.dto.EmployeeRequestDto;
import com.spring_rest.rest_app.dto.EmployeeResponseDto;
import com.spring_rest.rest_app.entity.Employee;
import com.spring_rest.rest_app.exception.EmployeeNotFoundException;
import com.spring_rest.rest_app.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //EmployeeResponseDto ----> Output
    //EmployeeRequestDto ----> Input

    //MAP ENTITIES TO EmployeeDTO
    private EmployeeResponseDto mapToEmployeeDto(Employee employee) {
        return new EmployeeResponseDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    //GET ALL EMPLOYEES
    public Page<EmployeeResponseDto> getAllEmployees(int page, int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("id").ascending()
        );

        return employeeRepository.findAll(pageable).map(this::mapToEmployeeDto);
    }

    //GET EMPLOYEES BY ID
    public EmployeeResponseDto getEmployeesById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id: " + id + " not found"));

        return mapToEmployeeDto(employee);
    }




    //ADD EMPLOYEES
    public EmployeeResponseDto addEmployee(EmployeeRequestDto employeeRequestDto) {

        Employee employee = new Employee(); // can be written inside constructor but should be initialized in employee entity
        employee.setFirstName(employeeRequestDto.firstName());
        employee.setLastName(employeeRequestDto.lastName());
        employee.setEmail(employeeRequestDto.email());

        Employee saved = employeeRepository.save(employee);//Insert --> NULL / Update --> Exist
        return mapToEmployeeDto(saved);
    }

    //UPDATE EMPLOYEE BY ID
    public EmployeeResponseDto updateEmployee(Long id ,EmployeeRequestDto employeeRequestDto) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id: " + id + " not found"));

        employee.setFirstName(employeeRequestDto.firstName());
        employee.setLastName(employeeRequestDto.lastName());
        employee.setEmail(employeeRequestDto.email());

        Employee save = employeeRepository.save(employee);
        return mapToEmployeeDto(save);

    }

    //PATCH EMPLOYEE
    public EmployeeResponseDto patchEmployee(Long employeeId, EmployeePatchDto employeePatchDto) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id: " + employeeId + " not found"));

        if(employeePatchDto.firstName() != null) {
            employee.setFirstName(employeePatchDto.firstName());
        }
        if(employeePatchDto.lastName() != null){
            employee.setLastName(employeePatchDto.lastName());
        }
        if(employeePatchDto.email() != null) {
            employee.setEmail(employeePatchDto.email());
        }

        Employee patched = employeeRepository.save(employee);
        return mapToEmployeeDto(patched);
    }

    //REMOVE EMPLOYEE BY ID
    public void removeEmployeeById(Long id) {
        if (!employeeRepository.existsById(id)) { // use this instead of findById()--> load Entities which is Unnecessary
            throw new EmployeeNotFoundException("Employee with id: " + id + " not found");
        }
        employeeRepository.deleteById(id);

    }


}
