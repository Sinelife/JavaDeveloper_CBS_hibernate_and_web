package com.cbsystematics.edu.homework05.dao.impl;

import com.cbsystematics.edu.homework05.dao.StudentDAO;
import com.cbsystematics.edu.homework05.entities.Student;

import javax.persistence.*;
import java.util.List;

public class JPAMethodsStudentDAOImpl implements StudentDAO {

    private static final String GET_ALL_QUERY = "select s from Student s";


    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.cbs.edu.jpa");
    private EntityManager em = emf.createEntityManager();

    @Override
    public String getNameById(Integer id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        String name = em.find(Student.class, id).getFirstName();

        transaction.commit();
        return name;
    }

    @Override
    public Student get(Integer id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Student student = em.find(Student.class, id);

        transaction.commit();
        return student;
    }

    @Override
    public Student create(Student student) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(student);

        transaction.commit();
        return student;
    }

    @Override
    public Student update(Student student) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.merge(student);

        transaction.commit();
        return student;
    }

    @Override
    public void delete(Integer id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Student student = em.find(Student.class, id);
        em.remove(student);

        transaction.commit();
    }


    /**
     * Не нашел способа через методы EntityManager для получения всех
     * элементов таблицы потому использую реализацию через JPQL
     */
    @Override
    public List<Student> getAll() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query query = em.createQuery(GET_ALL_QUERY);
        List<Student> resultList = query.getResultList();

        transaction.commit();
        return resultList;
    }

    @Override
    public void close() {
        System.out.println("All closing");
        em.close();
        emf.close();
    }
}
