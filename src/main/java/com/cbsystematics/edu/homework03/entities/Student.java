package com.cbsystematics.edu.homework03.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "test_students")
public class Student {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String surname;

    private Integer age;

    private Double averageMark;

    public Student(String name, String surname, Integer age, Double averageMark) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.averageMark = averageMark;
    }
}
