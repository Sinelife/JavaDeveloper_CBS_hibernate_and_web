package com.cbsystematics.edu.homework04.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.OneToMany;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "roles")
public class Role extends AbstractEntity{
    private String name;

    //@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<User> users;


    public Role(String name) {
        this.name = name;
    }
}
