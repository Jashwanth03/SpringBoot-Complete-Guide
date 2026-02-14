package com.spring_rest.rest_app.service;


import com.spring_rest.rest_app.dto.EmployeeRequestDto;
import com.spring_rest.rest_app.dto.EmployeeResponseDto;
import com.spring_rest.rest_app.entity.Employee;
import com.spring_rest.rest_app.exception.EmployeeNotFoundException;
import com.spring_rest.rest_app.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
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

    //MAP ENTITIES TO EMPLOYEEDTO
    private EmployeeResponseDto mapToEmployeeDto(Employee employee) {
        return new EmployeeResponseDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }


    //ADD EMPLOYEES
    public EmployeeResponseDto addEmployee(EmployeeRequestDto employeeRequestDto) {

        Employee employee = new Employee();
        employee.setFirstName(employeeRequestDto.firstName());
        employee.setLastName(employeeRequestDto.lastName());
        employee.setEmail(employeeRequestDto.email());

        Employee saved = employeeRepository.save(employee);//Insert --> NULL / Update --> Exist
        return mapToEmployeeDto(saved);
    }


    public void removeEmployeeById(Long id) {
        if (!employeeRepository.existsById(id)) { // use this instead of findById()--> load Entities which is Unnecessary
            throw new EmployeeNotFoundException("Employee with id: " + id + " not found");
        }
        employeeRepository.deleteById(id);

    }
}
