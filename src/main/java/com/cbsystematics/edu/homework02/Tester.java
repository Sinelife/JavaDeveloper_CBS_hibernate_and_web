package com.cbsystematics.edu.homework02;


import com.cbsystematics.edu.homework02.entities.Product;
import com.cbsystematics.edu.homework02.entities.User;
import com.cbsystematics.edu.homework02.orm.EntityManager;
import com.cbsystematics.edu.homework02.orm.EntityManagerService;

import java.util.List;


public class Tester {
    public static void main(String[] args) {
        EntityManager entityManager = new EntityManager();


        /**1) getAll test*/
        System.out.println("Список всех пользователей");
        List<User> userList = entityManager.getAll(User.class);
        EntityManagerService.outputList(userList);

        System.out.println("\nСписок всех продуктов");
        List<Product> productList = entityManager.getAll(Product.class);
        EntityManagerService.outputList(productList);



        /**2) create test*/
        //System.out.println("\n\n\n");
        //User user = new User("Дан ил", "Данин", "daaaanil", "ddddcweic", "danil@meta.ua");
        //System.out.println("Добавление в бд пользователя - " + user);
        //entityManager.create(user);

        //Product product = new Product("Телевизор", "Телевизор какойто", "Какойто", 98000);
        //System.out.println("Добавление в бд продукт - " + product);
        //entityManager.create(product);




        /**3) update test*/
        //System.out.println("\n\n\n");
        //Integer id = 2;
        //User userToUpdate = new User(id,"Антон", "Антонов", "aa", "aaaaaaaaaaa", "aa@meta.ua");
        //System.out.println("Обновляем пользователя по id = " + id);
        //System.out.println("На следующие значения - " + userToUpdate);
        //entityManager.update(userToUpdate);

        //id = 2;
        //Product productToUpdate = new Product(id, "Смартфонннн", "Смартфоннннн ктото", "Ктото тото", 100000);
        //System.out.println("Обновляем продукт по id = " + id);
        //System.out.println("На следующие значения - " + productToUpdate);
        //entityManager.update(productToUpdate);




        /**4) delete test*/
        //System.out.println("\n\n\n");
        //Integer idToDelete = 19;
        //System.out.println("Удаляем пользователя по id = " + idToDelete);
        //entityManager.delete(idToDelete, User.class);

        //idToDelete = 1;
        //System.out.println("Удаляем продукт по id = " + idToDelete);
        //entityManager.delete(idToDelete, Product.class);



        /**5) get test*/
        System.out.println("\n\n\n");
        Integer idToGet = 2;
        System.out.println("Получаем пользователя по id = " + idToGet);
        User userToGet = entityManager.get(idToGet, User.class);
        System.out.println(userToGet);

        System.out.println("\n");
        idToGet = 2;
        System.out.println("Получаем продукт по id = " + idToGet);
        Product productToGet = entityManager.get(idToGet, Product.class);
        System.out.println(productToGet);

    }
}
