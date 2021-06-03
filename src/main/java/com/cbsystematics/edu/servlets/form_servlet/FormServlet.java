package com.cbsystematics.edu.servlets.form_servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.write(new String(Files.readAllBytes(Paths.get("C:\\Users\\Ярослав\\java-developer-web-ponny\\yaroslav\\src\\main\\webapp\\WEB-INF\\form.html"))));
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println(username + " " + password);
        String template = new String(Files.readAllBytes(Paths.get("C:\\Users\\Ярослав\\java-developer-web-ponny\\yaroslav\\src\\main\\webapp\\WEB-INF\\welcome.html")));
        template = template.replaceAll("<-username->", username);
        resp.getWriter().write(template);

    }
}