package com.cbsystematics.edu.homework02.dao;

import com.cbsystematics.edu.homework02.config.ConnectionFactory;
import com.cbsystematics.edu.homework02.entities.AbstractEntity;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDAO<T extends AbstractEntity> implements DAO<T> {

    protected Connection connection;

    public AbstractDAO() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public List<T> getAll() {
        return null;
    }

}
