package com.cbsystematics.edu.homework02.entities;


import com.cbsystematics.edu.homework02.orm.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEntity {

    @Id(name = "id")
    protected Integer id;
}
