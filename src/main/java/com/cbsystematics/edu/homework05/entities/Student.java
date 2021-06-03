package com.cbsystematics.edu.homework05.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@com.cbsystematics.edu.homework02.orm.Entity
@com.cbsystematics.edu.homework02.orm.Table(name = "students_tiny_hibernate")
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "students_jpa_sql")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends AbstractEntity {

    @com.cbsystematics.edu.homework02.orm.Column(name = "first_name")
    @Column(name = "first_name")
    private String firstName;

    @com.cbsystematics.edu.homework02.orm.Column(name = "last_name")
    @Column(name = "last_name")
    private String lastName;

    private Integer age;

    @com.cbsystematics.edu.homework02.orm.Column(name = "average_mark")
    @Column(name = "average_mark")
    private Double averageMark;

    public Student(Integer id, String firstName, String lastName, Integer age, Double averageMark) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.averageMark = averageMark;
    }


    @Override
    public String toString() {
        return "Student{"
                + "firstName = " + firstName
                + ", lastName = " + lastName
                + ", age = " + age
                + ", averageMark = " + averageMark
                + ", id = " + id
                + '}';
    }
}
