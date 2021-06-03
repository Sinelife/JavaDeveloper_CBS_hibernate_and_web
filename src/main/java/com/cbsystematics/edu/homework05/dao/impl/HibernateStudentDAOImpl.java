package com.cbsystematics.edu.homework05.dao.impl;

import com.cbsystematics.edu.homework05.config.HibernateUtils;
import com.cbsystematics.edu.homework05.dao.StudentDAO;
import com.cbsystematics.edu.homework05.entities.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateStudentDAOImpl implements StudentDAO {

    private static final String DELETE_QUERY = "from Student where id = :id";
    private static final String SELECT_STUDENT_NAME_BY_ID_QUERY = "select s.firstName from Student s where s.id = :id";


    @Override
    public String getNameById(Integer id) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery(SELECT_STUDENT_NAME_BY_ID_QUERY)
                .setInteger("id", id);
        String name = (String) query.uniqueResult();

        transaction.commit();
        session.close();
        return name;
    }

    @Override
    public Student get(Integer id) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery(DELETE_QUERY)
                .setInteger("id", id);
        Student student = (Student) query.uniqueResult();

        transaction.commit();
        session.close();
        return student;
    }

    @Override
    public Student create(Student student) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        session.save(student);

        transaction.commit();
        session.close();
        return student;
    }

    @Override
    public Student update(Student student) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        session.update(student);

        transaction.commit();
        session.close();
        return student;
    }

    @Override
    public void delete(Integer id) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery(DELETE_QUERY)
                .setInteger("id", id);
        Student student = (Student) query.uniqueResult();
        session.delete(student);

        transaction.commit();
        session.close();
    }

    @Override
    public List<Student> getAll() {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        List students = session.createQuery("from Student").list();

        transaction.commit();
        session.close();
        return students;
    }

    @Override
    public void close() {
        HibernateUtils.close();
    }
}
