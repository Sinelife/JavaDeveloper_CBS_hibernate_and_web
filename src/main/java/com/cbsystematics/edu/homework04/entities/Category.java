package com.cbsystematics.edu.homework04.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "categories")
public class Category extends AbstractEntity {

    private String name;


    //@ManyToOne
    //@JoinColumn(name = "parent_category_id")
    private Category parentCategory;


    public Category(String name) {
        this.name = name;
    }
}
