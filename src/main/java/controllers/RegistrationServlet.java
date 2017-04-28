package main.java.controllers;

import main.java.model.entity.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static main.java.controllers.LoginServlet.service;

public class RegistrationServlet extends HelloServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registr.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        String repeat = req.getParameter("repeat");
        Users userRegistration = service.setUserRegistration(name, email, pass);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registr.jsp");
        dispatcher.forward(req, resp);
    }
}
