package com.cbsystematics.edu.homework05.dao.impl;

import com.cbsystematics.edu.homework05.dao.StudentDAO;
import com.cbsystematics.edu.homework05.entities.Student;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

public class CriteriaStudentDAOImpl implements StudentDAO {

    private static final String INSERT_QUERY = "INSERT INTO students_jpa_sql(first_name, last_name, age, average_mark) VALUES (?1,?2,?3,?4)";

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.cbs.edu.jpa");
    private EntityManager em = emf.createEntityManager();



    @Override
    public String getNameById(Integer id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = cb.createQuery(String.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        criteriaQuery.select(root.get("firstName"))
                .where(cb.equal(root.get("id"), id));
        TypedQuery<String> typedQuery = em.createQuery(criteriaQuery);
        String name = typedQuery.getSingleResult();

        transaction.commit();
        return name;
    }

    @Override
    public Student get(Integer id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = cb.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        criteriaQuery.select(root)
                .where(cb.equal(root.get("id"), id));
        TypedQuery<Student> typedQuery = em.createQuery(criteriaQuery);
        Student student = typedQuery.getSingleResult();

        transaction.commit();
        return student;
    }

    /**Не смог найти способа для создания запроса insert into в критериях
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

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Student> update = cb.createCriteriaUpdate(Student.class);
        Root root = update.from(Student.class);
        update.set("firstName", student.getFirstName())
                .set("lastName", student.getLastName())
                .set("age", student.getAge())
                .set("averageMark", student.getAverageMark());
        update.where(cb.equal(root.get("id"), student.getId()));
        em.createQuery(update).executeUpdate();

        transaction.commit();
        return student;
    }

    @Override
    public void delete(Integer id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Student> delete = cb.createCriteriaDelete(Student.class);
        Root root = delete.from(Student.class);
        delete.where(cb.equal(root.get("id"), id));
        em.createQuery(delete).executeUpdate();

        transaction.commit();
    }

    @Override
    public List<Student> getAll() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = cb.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        criteriaQuery.select(root);
        TypedQuery<Student> typedQuery = em.createQuery(criteriaQuery);
        List<Student> resultList = typedQuery.getResultList();

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
