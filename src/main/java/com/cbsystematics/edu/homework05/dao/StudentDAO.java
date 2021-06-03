package com.cbsystematics.edu.homework05.dao;


import com.cbsystematics.edu.homework05.entities.Student;


public interface StudentDAO extends DAO<Student> {
    String getNameById(Integer id);
}
