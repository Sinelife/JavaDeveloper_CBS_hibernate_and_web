package com.cbsystematics.edu.homework02.orm;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {

    public <T> List<T> getAll(Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try {
            String sql = EntityManagerService.getSqlQueryGetAll(clazz);
            Statement statement = Connector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                T obj = clazz.newInstance();
                EntityManagerService.setAllValuesToFields(obj, resultSet);
                list.add(obj);
            }
            resultSet.close();
            statement.close();
            Connector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;
    }

    public <T> T create(T t) {
        String sql;
        PreparedStatement statement;
        try {
            sql = EntityManagerService.getSqlQueryForCreate(t);
            statement = Connector.getConnection().prepareStatement(sql);
            statement.execute();
            //statement.execute(sql, Statement.RETURN_GENERATED_KEYS);
            //ResultSet resultSet = statement.getGeneratedKeys();
            //if(resultSet.next()) {
            //    Object id = resultSet.getObject(1);
            //    Field idField = EntityManagerService.getPrimaryIdField(t);
            //    System.out.println(id);
            //    idField.set(t, id);
            //}
            statement.close();
            Connector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public <T> T update(T t) {
        String sql;
        PreparedStatement statement;
        try {
            sql = EntityManagerService.getSqlQueryForUpdate(t);
            statement = Connector.getConnection().prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
            Connector.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public <T, I> void delete(I id, Class<T> clazz) {
        String sql;
        try {
            sql = EntityManagerService.getSqlQueryForDelete(id, clazz);
            PreparedStatement statement = Connector.getConnection().prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
            Connector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T, I> T get(I id, Class<T> clazz) {
        T t = null;
        try {
            String sql = EntityManagerService.getSqlQueryForGet(id, clazz);
            Statement statement = Connector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            t = clazz.newInstance();
            while (resultSet.next()) {
                EntityManagerService.setAllValuesToFields(t, resultSet);
            }
            resultSet.close();
            statement.close();
            Connector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }


}
