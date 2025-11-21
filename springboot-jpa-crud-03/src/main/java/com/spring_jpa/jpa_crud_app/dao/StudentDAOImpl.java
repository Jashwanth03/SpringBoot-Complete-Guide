package com.spring_jpa.jpa_crud_app.dao;

import com.spring_jpa.jpa_crud_app.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class StudentDAOImpl implements StudentDAO{

    //define EntityManager Field
    private EntityManager entityManager;

    //inject EntityManager Using Constructor Injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional //Everything succeeds → changes saved | Something fails → rollback
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> theQuery = entityManager.createQuery("From Student",Student.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> theQuery = entityManager.createQuery("From Student WHERE lastName =:theData",Student.class);
        theQuery.setParameter("theData",lastName);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
            entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        //retrieve Object with id
        Student theStudent = entityManager.find(Student.class,id);

        //Delete
        entityManager.remove(theStudent);

    }

    @Override
    @Transactional
    public int deleteAll() {
        int deletedRows = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return deletedRows;
    }

}
