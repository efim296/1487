package main.java.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static main.java.controllers.LoginServlet.service;

public class TasksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session =  req.getSession();
        String token = (String)session.getAttribute("token");
        resp.setContentType("text/html;charset=utf-8");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/tasks.jsp");
        boolean hasUser = service.hasUser(token);
        if (!hasUser) {
            resp.sendRedirect("/z/login");
        }
   dispatcher.forward(req, resp);
  }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/tasks.jsp");

        dispatcher.forward(req, resp);
        super.doPost(req, resp);
    }
}
