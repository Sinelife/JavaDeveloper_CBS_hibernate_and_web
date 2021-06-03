package com.cbsystematics.edu.homework01.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<E> {

    E getElement(Integer id) throws SQLException;

    E createElement(E elem) throws SQLException;

    E updateElement(E elem) throws SQLException;

    void deleteElement(Integer id) throws SQLException;

    List<E> getAllElements() throws SQLException;
}
