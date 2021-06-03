package com.cbsystematics.edu.homework04.entities;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Version
    protected Integer version;
}