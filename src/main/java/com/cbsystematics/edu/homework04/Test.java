package com.cbsystematics.edu.homework04;


import com.cbsystematics.edu.homework04.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.HashSet;

public class Test {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.cbs.edu.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        addData(entityManager);

        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();

    }



    public static void addData(EntityManager entityManager) {

        Role admin = new Role("Admin");
        Role simpleUser = new Role("Simple User");
        entityManager.persist(admin);
        entityManager.persist(simpleUser);

        User andrey = new User("andrey", "dewpoferimf");
        UserDetails andreyDetails = new UserDetails("Андрей", "Андреев", "aaa@gmail.com", "4023-242-24");
        andrey.setUserDetails(andreyDetails);
        andrey.setRole(admin);
        entityManager.persist(andreyDetails);
        entityManager.persist(andrey);

        User maxim = new User("maxim", "voilesmkofi");
        UserDetails maximDetails = new UserDetails("Максим", "Петров", "maxpet@gmail.com", "934343-24");
        maxim.setUserDetails(maximDetails);
        maxim.setRole(simpleUser);
        entityManager.persist(maximDetails);
        entityManager.persist(maxim);


        Category popular = new Category("Популярное");
        entityManager.persist(popular);
        Category technique = new Category("Техника");
        entityManager.persist(technique);
        Category smartphones = new Category("Смартфоны");
        smartphones.setParentCategory(technique);
        entityManager.persist(smartphones);
        Category tvs = new Category("Телевизоры");
        tvs.setParentCategory(technique);
        entityManager.persist(tvs);



        Product tv = new Product("Телевизор 100", "Company100", 50000);
        tv.setCategories(new HashSet<>(Arrays.asList(tvs, technique)));
        entityManager.persist(tv);

        Product galaxySSix = new Product("Samsung Galaxy S6", "Samsung", 20000);
        galaxySSix.setCategories(new HashSet<>(Arrays.asList(smartphones, technique, popular)));
        entityManager.persist(galaxySSix);


        Order quickOrder = new Order("Срочная доставка");
        quickOrder.setProducts(new HashSet<>(Arrays.asList(galaxySSix, tv)));
        quickOrder.setUser(andrey);
        Order monthOrder = new Order("Доставка в течении месяца");
        monthOrder.setProducts(new HashSet<>(Arrays.asList(galaxySSix)));
        monthOrder.setUser(maxim);
        entityManager.persist(quickOrder);
        entityManager.persist(monthOrder);



        Discount blackFridayDiscount = new Discount("Черная пятница", 40.5);
        blackFridayDiscount.setProducts(new HashSet<>(Arrays.asList(galaxySSix, tv)));
        Discount tvDiscount = new Discount("Распродажа старых телевизоров", 80.0);
        tvDiscount.setProducts(new HashSet<>(Arrays.asList(tv)));
        Discount smallDiscount = new Discount("Маленькая кидка", 5.0);
        smallDiscount.setProducts(new HashSet<>(Arrays.asList(galaxySSix)));
        entityManager.persist(blackFridayDiscount);
        entityManager.persist(tvDiscount);
        entityManager.persist(smallDiscount);

    }
}
