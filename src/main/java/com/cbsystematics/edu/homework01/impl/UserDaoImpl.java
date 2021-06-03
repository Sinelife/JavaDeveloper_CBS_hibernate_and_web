package com.cbsystematics.edu.homework01.impl;

import com.cbsystematics.edu.homework01.Connector;
import com.cbsystematics.edu.homework01.dao.UserDao;
import com.cbsystematics.edu.homework01.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public User getElement(Integer id) throws SQLException {
        Statement statement = Connector.getConnection().createStatement();
        String sql = "SELECT first_name, last_name, username, password, email FROM z_users WHERE id = " + id;
        ResultSet resultSet = statement.executeQuery(sql);
        User user = null;
        while (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            user = new User(id, firstName, lastName, username, password, email);
        }
        return user;
    }

    @Override
    public User createElement(User user) throws SQLException {
        String sql = "INSERT INTO z_users (first_name, last_name, username, password, email) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = Connector.getConnection().prepareStatement(sql);
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getUsername());
        statement.setString(4, user.getPassword());
        statement.setString(5, user.getEmail());
        statement.executeUpdate();
        return user;
    }

    @Override
    public User updateElement(User user) throws SQLException {
        String sql = "UPDATE z_users SET first_name = ?, last_name = ?, username = ?, password = ?, email = ? WHERE id = " + user.getId();
        PreparedStatement statement = Connector.getConnection().prepareStatement(sql);
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getUsername());
        statement.setString(4, user.getPassword());
        statement.setString(5, user.getEmail());
        statement.executeUpdate();
        return user;
    }

    @Override
    public void deleteElement(Integer id) throws SQLException {
        String sql = "DELETE FROM z_users WHERE z_users.id = " + id;
        PreparedStatement statement = Connector.getConnection().prepareStatement(sql);
        statement.executeUpdate();
    }

    @Override
    public List<User> getAllElements() throws SQLException {
        Statement statement = Connector.getConnection().createStatement();
        String sql = "SELECT id, first_name, last_name, username, password, email FROM z_users";
        ResultSet resultSet = statement.executeQuery(sql);
        List<User> userList = new LinkedList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            userList.add(new User(id, firstName, lastName, username, password, email));
        }
        return userList;
    }
}
