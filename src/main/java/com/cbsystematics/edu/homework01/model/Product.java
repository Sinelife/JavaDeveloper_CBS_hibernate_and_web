package com.cbsystematics.edu.homework01.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private Integer id;
    private String type;
    private String name;
    private String producer;
    private Integer price;


    public Product(String type, String name, String producer, Integer price) {
        this.type = type;
        this.name = name;
        this.producer = producer;
        this.price = price;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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