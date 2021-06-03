package com.cbsystematics.edu.homework05.dao.impl;

import com.cbsystematics.edu.homework05.dao.StudentDAO;
import com.cbsystematics.edu.homework05.entities.Student;

import javax.persistence.*;
import java.util.List;

public class SQLStudentDAOImpl implements StudentDAO {

    private static final String SELECT_ALL_QUERY = "SELECT * FROM students_jpa_sql";
    private static final String DELETE_QUERY = "DELETE FROM students_jpa_sql WHERE id = ?1";
    private static final String SELECT_QUERY = "SELECT * FROM students_jpa_sql WHERE id = ?1";
    private static final String UPDATE_QUERY = "UPDATE students_jpa_sql SET first_name = ?1, last_name = ?2, age = ?3, average_mark = ?4 WHERE id = ?5";
    private static final String INSERT_QUERY = "INSERT INTO students_jpa_sql(first_name, last_name, age, average_mark) VALUES (?1,?2,?3,?4)";
    private static final String GET_STUDENT_NAME_BY_ID = "SELECT first_name FROM students_jpa_sql WHERE id = ?1";


    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.cbs.edu.jpa");
    private EntityManager em = emf.createEntityManager();


    @Override
    public String getNameById(Integer id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query nativeQuery = em.createNativeQuery(GET_STUDENT_NAME_BY_ID)
                .setParameter(1, id);
        String name = (String) nativeQuery.getSingleResult();

        transaction.commit();
        return name;
    }

    @Override
    public Student get(Integer id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query nativeQuery = em.createNativeQuery(SELECT_QUERY, Student.class)
                .setParameter(1, id);
        Student student = (Student) nativeQuery.getSingleResult();

        transaction.commit();
        return student;
    }

    @Override
    public Student create(Student student) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.createNativeQuery(INSERT_QUERY)
                .setParameter(1, student.getFirstName())
                .setParameter(2, student.getLastName())
                .setParameter(3, student.getAge())
                .setParameter(4, student.getAverageMark())
                .executeUpdate();

        transaction.commit();
        return student;
    }

    @Override
    public Student update(Student student) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.createNativeQuery(UPDATE_QUERY)
                .setParameter(1, student.getFirstName())
                .setParameter(2, student.getLastName())
                .setParameter(3, student.getAge())
                .setParameter(4, student.getAverageMark())
                .setParameter(5, student.getId())
                .executeUpdate();

        transaction.commit();
        return student;
    }

    @Override
    public void delete(Integer id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.createNativeQuery(DELETE_QUERY)
                .setParameter(1, id)
                .executeUpdate();

        transaction.commit();
    }

    @Override
    public List<Student> getAll() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query nativeQuery = em.createNativeQuery(SELECT_ALL_QUERY, Student.class);
        List<Student> resultList = nativeQuery.getResultList();

        transaction.commit();

        return resultList;
    }


    public void close() {
        System.out.println("All closing");
        em.close();
        emf.close();
    }
}
