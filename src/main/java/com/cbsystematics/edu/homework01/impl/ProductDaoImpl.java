package com.cbsystematics.edu.homework01.impl;

import com.cbsystematics.edu.homework01.Connector;
import com.cbsystematics.edu.homework01.dao.ProductDao;
import com.cbsystematics.edu.homework01.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public Product getElement(Integer id) throws SQLException {
        Statement statement = Connector.getConnection().createStatement();
        String sql = "SELECT type, name, producer, price FROM z_products WHERE id = " + id;
        ResultSet resultSet = statement.executeQuery(sql);
        Product product = null;
        while (resultSet.next()) {
            String type = resultSet.getString("type");
            String name = resultSet.getString("name");
            String producer = resultSet.getString("producer");
            int price = resultSet.getInt("price");
            product = new Product(id, type, name, producer, price);
        }
        return product;
    }

    @Override
    public Product createElement(Product product) throws SQLException {
        String sql = "INSERT INTO z_products (type, name, producer, price) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = Connector.getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        statement.setString(1, product.getType());
        statement.setString(2, product.getName());
        statement.setString(3, product.getProducer());
        statement.setInt(4, product.getPrice());
        statement.executeUpdate();
        return product;
    }

    @Override
    public Product updateElement(Product product) throws SQLException {
        String sql = "UPDATE z_products SET type = ?, name = ?, producer = ?, price = ? WHERE id = ?";
        PreparedStatement statement = Connector.getConnection().prepareStatement(sql);
        statement.setString(1, product.getType());
        statement.setString(2, product.getName());
        statement.setString(3, product.getProducer());
        statement.setInt(4, product.getPrice());
        statement.setInt(5, product.getId());
        statement.executeUpdate();
        return product;
    }

    @Override
    public void deleteElement(Integer id) throws SQLException {
        String sql = "DELETE FROM z_products WHERE id = " + id;
        PreparedStatement statement = Connector.getConnection().prepareStatement(sql);
        statement.executeUpdate();
    }

    @Override
    public List<Product> getAllElements() throws SQLException {
        Statement statement = Connector.getConnection().createStatement();
        String sql = "SELECT id, type, name, producer, price FROM z_products";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Product> productList = new LinkedList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String type = resultSet.getString("type");
            String name = resultSet.getString("name");
            String producer = resultSet.getString("producer");
            int price = resultSet.getInt("price");
            productList.add(new Product(id, type, name, producer, price));
        }
        return productList;
    }




    @Override
    public List<Product> getAllProductsWithSameType(String chosenType) throws SQLException {
        Statement statement = Connector.getConnection().createStatement();
        String sql = "SELECT id, type, name, producer, price "
                + "FROM z_products WHERE type = '" + chosenType + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Product> productList = new LinkedList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String type = resultSet.getString("type");
            String name = resultSet.getString("name");
            String producer = resultSet.getString("producer");
            int price = resultSet.getInt("price");
            productList.add(new Product(id, type, name, producer, price));
        }
        return productList;
    }

    @Override
    public int getNumberOfAllProductsOfChosenType(String chosenType) throws SQLException {
        Statement statement = Connector.getConnection().createStatement();
        String sql = "SELECT count(*) AS count "
                + "FROM z_products WHERE type = '"
                + chosenType + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        int count = 0;
        while (resultSet.next()) {
            count = resultSet.getInt("count");
        }
        return count;
    }
}
