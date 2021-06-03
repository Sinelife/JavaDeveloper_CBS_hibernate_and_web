package com.cbsystematics.edu.homework05.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEntity {

    @com.cbsystematics.edu.homework02.orm.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;


}
