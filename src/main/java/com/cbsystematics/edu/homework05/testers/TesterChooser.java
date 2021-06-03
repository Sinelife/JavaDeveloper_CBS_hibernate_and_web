package com.cbsystematics.edu.homework05.testers;

import static com.cbsystematics.edu.homework05.testers.RealizationVariants.*;

import com.cbsystematics.edu.homework05.dao.StudentDAO;
import com.cbsystematics.edu.homework05.dao.impl.*;
import com.cbsystematics.edu.homework05.entities.Student;
import com.cbsystematics.edu.homework05.utils.RandomService;

import java.util.List;


public class TesterChooser {

    private StudentDAO studentDAO;
    private String realizationName;


    public void setRealizationVariant(RealizationVariants realizationVariant){
        realizationName = realizationVariant.getVariant();
        if (realizationVariant.equals(JDBC)) {
            studentDAO = new JDBCStudentDAOImpl();
        }
        if (realizationVariant.equals(TINY_HIBERNATE)) {
            studentDAO = new TinyHibernateStudentDAOImpl();
        }
        if (realizationVariant.equals(JPA_SQL)) {
            studentDAO = new SQLStudentDAOImpl();
        }
        if (realizationVariant.equals(JPA_JPQL)) {
            studentDAO = new JPQLStudentDAOImpl();
        }
        if (realizationVariant.equals(JPA_CRITERIA)) {
            studentDAO = new CriteriaStudentDAOImpl();
        }
        if (realizationVariant.equals(JPA_METHODS)) {
            studentDAO = new JPAMethodsStudentDAOImpl();
        }
        if (realizationVariant.equals(HIBERNATE_SESSION)) {
            studentDAO = new HibernateStudentDAOImpl();
        }
    }

    public void run(){
        System.out.println("\t\t\t\tTester for realization in " + realizationName + "!!!!\n\n");

        /**CREATE*/
        System.out.println("CREATE");
        System.out.println(studentDAO.create(RandomService.getRandomStudentData()));


        /**GET_ALL*/
        System.out.println("\n\n\nGET_ALL");
        List<Student> students = studentDAO.getAll();
        students.forEach(System.out::println);

        /**GET*/
        System.out.println("\n\n\nGET");
        System.out.println(studentDAO.get(1));


        /**UPDATE*/
        System.out.println("\n\n\nUPDATE");
        System.out.println("update: " + studentDAO.update(RandomService.getRandomStudentData(2)) + "\n");
        students = studentDAO.getAll();
        students.forEach(System.out::println);


        /**DELETE*/
        System.out.println("\n\n\nDELETE");
        int id = 34;
        studentDAO.delete(id);
        students = studentDAO.getAll();
        students.forEach(System.out::println);


        /**GET_NAME_BY_ID*/
        System.out.println("\n\n\nGET_NAME_BY_ID");
        id = 1;
        System.out.println("Name by id = " + id + ": " + studentDAO.getNameById(id));


        studentDAO.close();
    }




}
