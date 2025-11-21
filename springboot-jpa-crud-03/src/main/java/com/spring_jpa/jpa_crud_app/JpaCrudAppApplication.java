package com.spring_jpa.jpa_crud_app;

import com.spring_jpa.jpa_crud_app.dao.StudentDAO;
import com.spring_jpa.jpa_crud_app.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JpaCrudAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaCrudAppApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
        return runner ->{

//            createStudent(studentDAO);
            createMultipleStudent(studentDAO);
//            readStudent(studentDAO);
//            queryFindAllStudents(studentDAO);
//            queryFindByLastName(studentDAO);
//            queryUpdateStudent(studentDAO);
//            queryDeleteStudent(studentDAO);
//            queryDeleteAllStudents(studentDAO);

        };
    }

    private void queryDeleteAllStudents(StudentDAO studentDAO) {

        System.out.println("Deleting All Students....");
      int deletedRows =  studentDAO.deleteAll();

        System.out.println("Deleted "+deletedRows+" Rows!");
    }

    private void queryDeleteStudent(StudentDAO studentDAO) {
        int studentId = 4;
        System.out.println("Getting Student with Id : "+studentId);

        //Deleting the student object
        studentDAO.delete(studentId);
        //display
        System.out.println("Deleted the Student with Id : "+studentId);
    }

    private void queryUpdateStudent(StudentDAO studentDAO) {
        int studentId = 1;
        System.out.println("Getting Student with Id : "+studentId);

        Student theStudent = studentDAO.findById(studentId);
        //setting last name
        theStudent.setLastName("Ero");
        //updating the student object
        studentDAO.update(theStudent);
        //display
        System.out.println("Updated the Student's LastName : "+ theStudent);
    }

    private void queryFindByLastName(StudentDAO studentDAO) {

        List<Student> studentList = studentDAO.findByLastName("kumar");

        for(Student s : studentList){
            System.out.println("Found Student by last Name : "+s);
        }
    }

    private void queryFindAllStudents(StudentDAO studentDAO) {

        List<Student> studentList = studentDAO.findAll();

        for(Student s : studentList){
            System.out.println(s);
        }
    }

    private void readStudent(StudentDAO studentDAO) {
        //creating student object
        System.out.println("Creating new student object....");
        Student tempstudent = new Student(21,"jk","03","jk@gmail.com");

        //saving student object
        System.out.println("Saving the student object...");
        studentDAO.save(tempstudent);

        //display id
        int myId = tempstudent.getId();
        System.out.println("Saved Id : "+myId);
        //retrieve student based on Id
        System.out.println("Retrieving Id : "+myId);
        Student myStudent = studentDAO.findById(myId);
        //reading student......
        System.out.println("Found Student : "+myStudent);

    }

    private void createStudent(StudentDAO studentDAO) {
        //creating student object
        System.out.println("Creating new student object....");
        Student tempstudent = new Student(18,"Shree","rupa","shree@gmail.com");

        //saving student object
        System.out.println("Saving the student object...");
        studentDAO.save(tempstudent);

        System.out.println("Saved student id : " +tempstudent.getId());
    }

    private void createMultipleStudent(StudentDAO studentDAO) {
        //creating student object
        System.out.println("Creating new student objects....");
        Student s1 = new Student(21,"jash", "Ero", "jash@gmail.com");
        Student s2 = new Student(22,"Mady", "Kumar", "mady@gmail.com");
        Student s3 = new Student(22,"Mag", "Kanna", "mag@gmail.com");


        //saving student object
        System.out.println("Saving the student objects...");
        studentDAO.save(s1);
        studentDAO.save(s2);
        studentDAO.save(s3);

//        System.out.println("Saved student id : " +tempstudent.getId());
    }
}
