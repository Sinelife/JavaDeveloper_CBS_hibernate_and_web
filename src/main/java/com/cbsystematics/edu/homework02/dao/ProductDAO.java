package com.cbsystematics.edu.homework02.dao;

import com.cbsystematics.edu.homework02.entities.Product;



public interface ProductDAO extends DAO<Product> {
    Product getSerialNumber(Integer serialNumber);
}
