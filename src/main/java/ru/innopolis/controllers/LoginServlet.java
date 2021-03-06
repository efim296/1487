package ru.innopolis.controllers;


import ru.innopolis.model.entity.Users;
import ru.innopolis.services.UsersService;
import ru.innopolis.services.UsersServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    public static UsersService service = new UsersServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");

        if (!service.validate(login)) {
            req.setAttribute("error", "error");
        } else {
            Users user = service.getUserByEmail(login);
            if (password.equals(user.getPass()) && (user.getVerification())) {
                HttpSession session = req.getSession();
                session.setAttribute("token", user.getToken());
                dispatcher = getServletContext().getRequestDispatcher("/tasks");
            } else {
                req.setAttribute("error", "error");
            }
        }
        dispatcher.include(req, resp);
    }
}
