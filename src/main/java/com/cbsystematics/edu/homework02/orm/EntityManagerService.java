package com.cbsystematics.edu.homework02.orm;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManagerService {


    private static final String DELETE_QUERY_TEMPLATE = "DELETE FROM %s WHERE %s = %s";
    private static final String SELECT_ALL_QUERY_TEMPLATE = "SELECT * FROM %s";
    private static final String SELECT_QUERY_TEMPLATE = "SELECT * FROM %s WHERE %s = %s";
    private static final String INSERT_QUERY_TEMPLATE = "INSERT INTO %s(%s) VALUES(%s)";
    private static final String UPDATE_QUERY_TEMPLATE = "UPDATE %s SET %s WHERE %s = %s";



    /**---------------------------------------------GetAll------------------------------------------/
     /**
     * Метод, для создания универсального sql запроса для метода getAll
     */
    public static <T> String getSqlQueryGetAll(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T t =  clazz.newInstance();
        return String.format(SELECT_ALL_QUERY_TEMPLATE, getTableName(t));
    }


    /**
     * Метод, который записывает все значения полученые из ResultSet в поля обьекта
     */
    public static <T> void setAllValuesToFields(T t, ResultSet resultSet) throws SQLException, IllegalAccessException {
        List<Field> fields = new ArrayList<>();
        Collections.addAll(fields, t.getClass().getDeclaredFields());
        Collections.addAll(fields, t.getClass().getSuperclass().getDeclaredFields());
        for (Field field : fields) {
            Object columnValue = getColumnValue(field, resultSet);
            field.set(t, columnValue);
        }
    }





    /**--------------------------------------------Create--------------------------------------------/
     /**
     * Метод, для создания универсального sql запроса для метода create()
     */
    public static <T> String getSqlQueryForCreate(T t) {
        return String.format(INSERT_QUERY_TEMPLATE, getTableName(t), getColumnNamesInQuery(t), getColumnValuesInQuery(t));
    }

    /**
     * Метод, который заносит в запрос на создание элемента имена колонок таблицы
     */
    private static <T> String getColumnNamesInQuery(T t) {
        Class clazz = t.getClass();
        return Arrays.stream(clazz.getDeclaredFields())
                .map(EntityManagerService::getColumnNameFromAnnotation)
                .collect(Collectors.joining(", "));
    }


    /**
     * Метод, который заносит в запрос на создание элемента значение полей элемента
     * (Заполняет VALUES в запросе)
     */
    private static <T> String getColumnValuesInQuery(T t) {
        Class clazz = t.getClass();
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        List<Object> fieldValues = new ArrayList<>();
        fields.forEach(field -> fieldValues.add(getFieldValue(field, t)));
        return fieldValues.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }




    /**-----------------------------------------------Update----------------------------------------------*/

    /**
     * Метод, для создания универсального sql запроса для метода update()
     */
    public static <T> String getSqlQueryForUpdate(T t) {
        List<Field> fields = Arrays.asList(t.getClass().getDeclaredFields());
        List<String> fieldNamesAndValues = new ArrayList<>();
        fields.forEach(field -> fieldNamesAndValues.add(getColumnNameFromAnnotation(field) + " = " + getFieldValue(field, t)));
        String sqlSetPart = fieldNamesAndValues.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        Field idField = getIdField(t);
        return String.format(UPDATE_QUERY_TEMPLATE, getTableName(t), sqlSetPart, idField.getName(), getFieldValue(idField, t));
    }






    /**-----------------------------------------Delete--------------------------------------*/

    /**
     * Метод, для создания универсального sql запроса для метода delete()
     */
    public static <T, I> String getSqlQueryForDelete(I id, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T t = clazz.newInstance();
        return String.format(DELETE_QUERY_TEMPLATE, getTableName(t), getIdField(t).getName(), id);
    }






    /**-------------------------------------------Get--------------------------------------*/

    /**
     * Метод, для создания универсального sql запроса для метода get()
     */
    public static <T, I> String getSqlQueryForGet(I id, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T t = clazz.newInstance();
        return String.format(SELECT_QUERY_TEMPLATE, getTableName(t), getIdField(t).getName(), id);
    }






    /**----------------------------------------------Others-----------------------------------*/

    /**
     * Метод, который заносит значение поля строчного типа в кавычки, а значение всех остальных типов
     * просто преобразовывает в строку
     */
    public static <T> Object getFieldValue(Field field, T t) {
        field.setAccessible(true);
        Object value = null;
        try {
            value = field.get(t);
            if (field.getType().equals(String.class)) {
                value = "'" + value + "'";
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }


    /**
     * Метод, для получения из ResultSet значения для поля field
     */
    public static Object getColumnValue(Field field, ResultSet resultSet) throws SQLException {
        field.setAccessible(true);
        String columnName = getColumnNameFromAnnotation(field);
        return resultSet.getObject(columnName);
    }


    /**
     * Метод, который возвращает имя таблицы
     */
    private static <T> String getTableName(T t) {
        if (t.getClass().isAnnotationPresent(Entity.class)) {
            Annotation annotation = t.getClass().getAnnotation(Table.class);
            if (((Table) annotation).name().equals("")) {
                return t.getClass().getSimpleName().toLowerCase();
            } else {
                return ((Table) annotation).name();
            }
        } else {
            throw new IllegalMonitorStateException("Class " + t.getClass().getName() + " don't have annotation @Entity");
        }
    }


    /**
     * Метод, для получения из аннотации Column имени поля(колонки в таблице)
     * Если анотации нет то возвращаеться имя поля.
     */
    private static String getColumnNameFromAnnotation(Field field) {
        field.setAccessible(true);
        String columnName;
        if (field.isAnnotationPresent(Column.class)) {
            Annotation columnAnnotation = field.getAnnotation(Column.class);
            columnName = ((Column) columnAnnotation).name();
        } else {
            columnName = field.getName();
        }
        return columnName;
    }


    /**
     * Метод, который получает primary id из класа или его родителя AbstractEntity
     */
    public static <T> Field getIdField(T t) {
        List<Field> fields = new ArrayList<>();
        Collections.addAll(fields, t.getClass().getDeclaredFields());
        Collections.addAll(fields, t.getClass().getSuperclass().getDeclaredFields());
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }
        throw new IllegalMonitorStateException("Class " + t.getClass().getName() + " and his parent classes don't have field with annotation @Id");
    }


    /**
     * Метод, который выводит элементы списка с счетчиком
     */
    public static <E> void outputList(List<E> list) {
        int counter = 1;
        for (E e : list) {
            System.out.println(counter++ + ")" + e);
        }
    }
}