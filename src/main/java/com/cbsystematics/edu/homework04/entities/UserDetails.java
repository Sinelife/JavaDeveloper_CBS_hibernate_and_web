package com.cbsystematics.edu.homework04.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
//import javax.persistence.Entity;
import javax.persistence.OneToOne;
//import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "user_details")
public class UserDetails extends AbstractEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String phone;

    @OneToOne(mappedBy = "userDetails")
    private User user;


    public UserDetails(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserDetails{"
                + "firstName = " + firstName
                + ", lastName = " + lastName
                + ", email = " + email
                + ", phone = " + phone
                + ", user = " + user.getUsername()
                + '}';
    }
}
