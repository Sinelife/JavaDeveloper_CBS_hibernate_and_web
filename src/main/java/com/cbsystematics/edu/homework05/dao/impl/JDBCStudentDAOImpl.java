package com.cbsystematics.edu.homework05.dao.impl;

import com.cbsystematics.edu.homework05.config.Connector;
import com.cbsystematics.edu.homework05.dao.StudentDAO;
import com.cbsystematics.edu.homework05.entities.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCStudentDAOImpl implements StudentDAO {

    private static final String INSERT_QUERY = "INSERT INTO students_jdbc (first_name, last_name, age, average_mark) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM students_jdbc";
    private static final String UPDATE_QUERY = "UPDATE students_jdbc SET first_name = ?, last_name = ?, age = ?, average_mark = ? WHERE id = ?";
    private static final String GET_QUERY = "SELECT first_name, last_name, age, average_mark FROM students_jdbc WHERE id = %s";
    private static final String DELETE_QUERY = "DELETE FROM students_jdbc WHERE id = %s";
    private static final String SELECT_STUDENT_NAME_BY_ID = "SELECT first_name FROM students_jdbc WHERE id = %s";

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
        String sql = String.format(GET_QUERY, id);
        Student student = null;
        try {
            Statement statement = Connector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastname = resultSet.getString("last_name");
                int age = resultSet.getInt("age");
                double averageMark = resultSet.getDouble("average_mark");
                student = new Student(id, firstName, lastname, age, averageMark);
            }
            Connector.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public Student create(Student student) {
        PreparedStatement statement;
        try {
            statement = Connector.getConnection().prepareStatement(INSERT_QUERY);
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setInt(3, student.getAge());
            statement.setDouble(4, student.getAverageMark());
            statement.executeUpdate();
            Connector.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public Student update(Student student) {
        PreparedStatement statement;
        try {
            statement = Connector.getConnection().prepareStatement(UPDATE_QUERY);
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setInt(3, student.getAge());
            statement.setDouble(4, student.getAverageMark());
            statement.setInt(5, student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public void delete(Integer id) {
        String sql = String.format(DELETE_QUERY, id);
        try {
            PreparedStatement statement = Connector.getConnection().prepareStatement(sql);
            statement.executeUpdate();
            Connector.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        try {
            Statement statement = Connector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int age = resultSet.getInt("age");
                double averageMark = resultSet.getDouble("average_mark");
                students.add(new Student(id, firstName, lastName, age, averageMark));
            }
            Connector.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void close() {

    }
}

