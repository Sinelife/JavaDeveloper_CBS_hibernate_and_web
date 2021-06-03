package com.cbsystematics.edu.homework01;

//import com.cbsystematics.edu.homework01.impl.ProductDaoImpl;
//import com.cbsystematics.edu.homework01.model.Product;
import java.sql.SQLException;
//import java.util.List;

public class Tester {
    public static void main(String[] args) throws SQLException {
        Connector.connectToDatabase();
//        User user = new User(1,"Николай", "Белов", "belovvvv", "nick", "nick@gmail.com");
//        UserDaoImpl d = new UserDaoImpl();
//        try {
//            d.createElement(user);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(d.getElement(5));
//        d.updateElement(user);
//
//        List<User> list = d.getAllElements();
//        for (User u : list) {
//            System.out.println(u);
//        }


//        Product product = new Product("Телевизор", " LG 49UK6200PLA", "LG", 40000);
//        ProductDaoImpl p = new ProductDaoImpl();
//        Product product1 = p.getElement(2);
//
//        List<Product> list = p.getAllProductsWithSameType("Смартфон");
//        for (Product chosenProduct : list) {
//            System.out.println(chosenProduct);
//        }
//        System.out.println("\n");
//
//        System.out.println(p.getNumberOfAllProductsOfChosenType("Телевизор"));
    }
}
