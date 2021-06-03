package com.cbsystematics.edu.homework05.dao;


import com.cbsystematics.edu.homework05.entities.AbstractEntity;

import java.util.List;

public interface DAO<T extends AbstractEntity> {
    T get(Integer id);

    T create(T entity);

    T update(T entity);

    void delete(Integer id);

    List<T> getAll();



    /**ДЛя возможности закрывать все реализации с JPA*/
    void close();
}
