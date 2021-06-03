package com.cbsystematics.edu.homework05.testers.deprecated_tests;

import com.cbsystematics.edu.homework05.dao.StudentDAO;
import com.cbsystematics.edu.homework05.dao.impl.SQLStudentDAOImpl;
import com.cbsystematics.edu.homework05.entities.Student;
import com.cbsystematics.edu.homework05.utils.RandomService;

import java.util.List;

@Deprecated
public class TesterJPASQL {
    public static void main(String[] args) {
        StudentDAO studentDAO = new SQLStudentDAOImpl();

        /**CREATE*/
        System.out.println("CREATE");
        Student student = new Student("Adsd", "Xxxxx", 40, 90.0);
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
        studentDAO.delete(3);
        students = studentDAO.getAll();
        students.forEach(System.out::println);


        /**GET_NAME_BY_ID*/
        System.out.println("\n\n\nGET_NAME_BY_ID");
        int id = 1;
        System.out.println("Name by id = " + id + ": " + studentDAO.getNameById(id));


        studentDAO.close();
    }
}
