package com.cbsystematics.edu.homework05.dao.impl;


import com.cbsystematics.edu.homework05.dao.StudentDAO;
import com.cbsystematics.edu.homework05.entities.Student;

import javax.persistence.*;
import java.util.List;

public class JPQLStudentDAOImpl implements StudentDAO {

    private static final String INSERT_QUERY = "INSERT INTO students_jpa_sql(first_name, last_name, age, average_mark) VALUES (?1,?2,?3,?4)";
    private static final String GET_ALL_QUERY = "select s from Student s";
    private static final String DELETE_QUERY = "delete from Student s where s.id = ?1";
    private static final String UPDATE_QUERY = "update Student s set s.firstName = ?1, s.lastName = ?2, s.age = ?3, s.averageMark = ?4 where s.id = ?5";
    private static final String GET_QUERY = "select s from Student s where s.id = ?1";
    private static final String GET_STUDENT_NAME_BY_ID = "select s.firstName from Student s where s.id = ?1";


    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.cbs.edu.jpa");
    private EntityManager em = emf.createEntityManager();



    @Override
    public String getNameById(Integer id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query query = em.createQuery(GET_STUDENT_NAME_BY_ID)
                .setParameter(1, id);
        String name = (String) query.getSingleResult();

        transaction.commit();
        return name;
    }

    @Override
    public Student get(Integer id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query query = em.createQuery(GET_QUERY)
                .setParameter(1, id);
        Student student = (Student) query.getSingleResult();

        transaction.commit();
        return student;
    }

    /**Не смог найти способа для создания запроса insert into в JPQL
     * Поэтому просто дублировал вариант для простого sql*/
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

        Query query = em.createQuery(UPDATE_QUERY)
                .setParameter(1, student.getFirstName())
                .setParameter(2, student.getLastName())
                .setParameter(3, student.getAge())
                .setParameter(4, student.getAverageMark())
                .setParameter(5, student.getId());
        query.executeUpdate();

        transaction.commit();
        return student;
    }

    @Override
    public void delete(Integer id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query query = em.createQuery(DELETE_QUERY)
                .setParameter(1, id);
        query.executeUpdate();

        transaction.commit();
    }

    @Override
    public List<Student> getAll() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query query = em.createQuery(GET_ALL_QUERY);
        List<Student> resultList = query.getResultList();

        transaction.commit();

        return resultList;
    }

    public void close() {
        System.out.println("All closing");
        em.close();
        emf.close();
    }
}
