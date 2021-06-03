package com.cbsystematics.edu.homework01.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;


    public User(String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{"
                + "id = " + id
                + ", firstName = " + firstName
                + ", lastName = " + lastName
                + ", username = " + username
                + ", password = " + password
                + ", email = " + email
                + '}';
    }
}

