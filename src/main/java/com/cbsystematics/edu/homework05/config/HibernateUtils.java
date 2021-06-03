package com.cbsystematics.edu.homework05.config;

import com.cbsystematics.edu.homework05.entities.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

    private static SessionFactory sessionFactory = null;

    static {
        try {
            loadSessionFactory();
        } catch(Exception e) {
            System.err.println("Exception while initializing hibernate util.. ");
            e.printStackTrace();
        }
    }

    private static void loadSessionFactory(){

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Student.class);
        ServiceRegistry srvcReg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(srvcReg);
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    public static void close() {
        sessionFactory.close();
    }
}
