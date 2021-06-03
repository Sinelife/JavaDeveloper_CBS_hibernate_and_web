package com.cbsystematics.edu.homework02.entities;


import com.cbsystematics.edu.homework02.orm.Entity;
import com.cbsystematics.edu.homework02.orm.Table;
import lombok.*;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "z_products")
public class Product extends AbstractEntity {
    private String type;
    private String name;
    private String producer;
    private Integer price;

    public Product(Integer id, String type, String name, String producer, Integer price) {
        super(id);
        this.type = type;
        this.name = name;
        this.producer = producer;
        this.price = price;
    }


    @Override
    public String toString() {
        return "Product{"
                + "id = " + id
                + ", type = " + type
                + ", name = " + name
                + ", producer = " + producer
                + ", price = " + price
                + '}';
    }
}
