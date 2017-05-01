package main.java.controllers;

import main.java.model.entity.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static main.java.controllers.LoginServlet.service;

/**
 * Created by admin on 27.04.2017.
 */
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        resp.setContentType("text/html;charset=utf-8");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/auth.jsp");

        boolean isLogin = service.activateUser(token);
        if (isLogin) {
            // авторизация (запись в сессию токен)
            HttpSession session = req.getSession();
            session.setAttribute("token", token);
            resp.sendRedirect("/z/tasks");
        } else {
            resp.sendRedirect("/z/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/auth.jsp");

        dispatcher.forward(req, resp);
        super.doPost(req, resp);
    }
}

