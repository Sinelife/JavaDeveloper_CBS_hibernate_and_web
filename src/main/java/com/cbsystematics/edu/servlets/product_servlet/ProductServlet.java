package com.cbsystematics.edu.servlets.product_servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class ProductServlet extends HttpServlet {

    private static final List<Product> products = new ArrayList<>(asList(
            new Product(0, "IPhone X 256 GB", "This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.","Phones", 20000),
            new Product(1, "IPhone X 276 GB", "This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.","Phones", 22000),
            new Product(2, "IPhone X 400 GB", "This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.","Phones", 30000),
            new Product(3, "IPhone X 440 GB", "This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.","Phones",  33000)
    ));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqParameter = req.getParameter("action");
        if (reqParameter != null && !reqParameter.isEmpty()) {
            switch (reqParameter) {
                case "create": {
                    req.getRequestDispatcher("/new_product.jsp").forward(req, resp);
                    break;
                }
                case "delete": {
                    String idParam = req.getParameter("id");
                    if(idParam != null && !idParam.isEmpty()) {
                        int id = Integer.parseInt(idParam);
                        Product deletedProduct = null;
                        for (Product product : products) {
                            if(product.getId() == id){
                                deletedProduct = product;
                            }
                        }
                        products.remove(deletedProduct);
                    }
                    break;
                }
                case "update": {
                    String idParam = req.getParameter("id");
                    if(idParam != null && !idParam.isEmpty()) {
                        int id = Integer.parseInt(idParam);
                        Product updProduct = null;
                        for (Product product : products) {
                            if(product.getId() == id){
                                updProduct = product;
                            }
                        }

                        req.setAttribute("updProduct", updProduct);
                        req.getRequestDispatcher("/new_product.jsp").forward(req, resp);
                    }
                    break;
                }
            }
        }

        req.setAttribute("products", products);
        req.getRequestDispatcher("/main.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Integer price = Integer.valueOf(req.getParameter("price"));
        String category = req.getParameter("category");

        int id;
        Product product;
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            id = Integer.parseInt(idParam);
            product = new Product(id, title, description, category, price);
            products.set(id, product);
        } else {
            id = products.size();
            product = new Product(id, title, description, category, price);
            products.add(product);
        }

        req.setAttribute("products", products);
        req.getRequestDispatcher("/main.jsp").forward(req, resp);
    }
}
