package com.cbsystematics.edu.servlets.first_servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //System.out.println("Hello, world !!!!!!!!");
        PrintWriter writer = resp.getWriter();
        writer.write(new String(Files.readAllBytes(Paths.get("C:\\Users\\Ярослав\\java-developer-web-ponny\\yaroslav\\src\\main\\webapp\\WEB-INF\\hello.html"))));
    }


}
