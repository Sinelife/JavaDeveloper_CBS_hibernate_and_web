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
//@Table(name = "smallusers")
public class SmallUser {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String password;

    public SmallUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}