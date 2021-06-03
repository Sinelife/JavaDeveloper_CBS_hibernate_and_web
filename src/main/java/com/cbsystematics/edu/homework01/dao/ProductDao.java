package com.cbsystematics.edu.homework01.dao;

import com.cbsystematics.edu.homework01.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao extends GenericDao<Product> {

    List<Product> getAllProductsWithSameType(String type) throws SQLException;

    int getNumberOfAllProductsOfChosenType(String type) throws SQLException;
}
