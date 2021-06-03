package com.cbsystematics.edu.homework04.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "users")
public class User extends AbstractEntity {

    private String username;

    private String password;


    @OneToOne
    @JoinColumn(name = "user_details_id")
    private UserDetails userDetails;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}