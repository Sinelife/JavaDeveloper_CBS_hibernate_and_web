package com.cbsystematics.edu.homework03;

import com.cbsystematics.edu.homework03.entities.SmallUser;
import com.cbsystematics.edu.homework03.entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Test {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.cbs.edu.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        for (int i = 0; i < 10; i++) {
            SmallUser user = new SmallUser("Qwerty", "hiuhhihncvr");
            entityManager.persist(user);
        }
        transaction.commit();

        transaction.begin();
        Student studentOne = new Student("Петр", "Петров", 20, 78.8);
        entityManager.persist(studentOne);
        Student studentTwo = new Student("Антон", "Антонов", 22, 98.8);
        entityManager.persist(studentTwo);
        transaction.commit();


        entityManager.close();
        entityManagerFactory.close();

    }
}
