package com.cbsystematics.edu.homework02.dao;

import com.cbsystematics.edu.homework02.entities.AbstractEntity;

import java.util.List;

public interface DAO<T extends AbstractEntity> {
    T get(Integer id);

    T create(T entity);

    T update(T entity);

    void delete(Integer id);

    List<T> getAll();
}
