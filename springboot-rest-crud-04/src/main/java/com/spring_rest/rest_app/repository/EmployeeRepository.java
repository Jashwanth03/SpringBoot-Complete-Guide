package com.spring_rest.rest_app.repository;


import com.spring_rest.rest_app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository ---> Not required when extnding JpaRepo
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
