package com.cbsystematics.edu.homework02.dao;

import com.cbsystematics.edu.homework02.entities.User;


public interface UserDAO extends DAO<User> {
    User getByUsername(String username);

}
