package com.cbsystematics.edu.homework05.dao.impl;

import com.cbsystematics.edu.homework02.orm.EntityManager;
import com.cbsystematics.edu.homework05.config.Connector;
import com.cbsystematics.edu.homework05.dao.StudentDAO;
import com.cbsystematics.edu.homework05.entities.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TinyHibernateStudentDAOImpl implements StudentDAO {

    private static final String SELECT_STUDENT_NAME_BY_ID = "SELECT first_name FROM students_tiny_hibernate WHERE id = %s";

    private EntityManager entityManager = new EntityManager();

    @Override
    public String getNameById(Integer id) {
        String sql = String.format(SELECT_STUDENT_NAME_BY_ID, id);
        String name = null;
        try {
            Statement statement = Connector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                name = resultSet.getString("first_name");
            }
            Connector.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    @Override
    public Student get(Integer id) {
        return entityManager.get(id, Student.class);
    }

    @Override
    public Student create(Student student) {
        entityManager.create(student);
        return student;
    }

    @Override
    public Student update(Student student) {
        entityManager.update(student);
        return student;
    }

    @Override
    public void delete(Integer id) {
        entityManager.delete(id, Student.class);
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = entityManager.getAll(Student.class);
        return students;
    }


    public void close() {

    }
}
