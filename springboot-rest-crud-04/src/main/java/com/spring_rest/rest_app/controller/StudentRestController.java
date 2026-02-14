package com.spring_rest.rest_app.controller;


import com.spring_rest.rest_app.entity.Student;
import com.spring_rest.rest_app.exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class StudentRestController {

    private List<Student> theStudents;

    //define @PostConstruct to load data once

    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Batman","vaval"));
        theStudents.add(new Student("Spooder","man"));
        theStudents.add(new Student("Jash","JK"));
    }

    @GetMapping("/students")
    public List<Student> getStudents(){

        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentBYId(@PathVariable  int studentId){


        if(studentId >= theStudents.size() || studentId < 0){
            throw new StudentNotFoundException("Student id - " +studentId+" not found");
        }
        return theStudents.get(studentId);
    }

}
