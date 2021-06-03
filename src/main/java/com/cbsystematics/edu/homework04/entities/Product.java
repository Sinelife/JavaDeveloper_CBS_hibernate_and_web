package com.cbsystematics.edu.homework04.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "products")
public class Product extends AbstractEntity {

    private String name;

    private String producer;

    private Integer price;



    @ManyToMany
    @JoinTable(name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;


    @ManyToMany(mappedBy = "products")
    private Collection<Discount> discounts;


    public Product(String name, String producer, Integer price) {
        this.name = name;
        this.producer = producer;
        this.price = price;
    }
}
