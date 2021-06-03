package com.cbsystematics.edu.homework04.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "discounts")
public class Discount extends AbstractEntity {

    private String name;

    private Double discountPercents;

    @ManyToMany
    @JoinTable(name = "products_discounts",
            joinColumns = @JoinColumn(name = "discount_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;


    public Discount(String name, Double discountPercents) {
        this.name = name;
        this.discountPercents = discountPercents;
    }
}
