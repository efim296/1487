package main.java.controllers;


import main.java.model.entity.Users;
import main.java.services.UsersService;
import main.java.services.UsersServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 18.04.2017.
 */
public class LoginServlet extends HttpServlet  {
    public  static UsersService service = new UsersServiceImpl();


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
        //boolean valid = Validator.validate(login);
         boolean valid = service.validate(login);
        Users user =  service.getUserByEmail(login);



        // сравнить  паролиэ

        if (password.equals(user.getPass()))
        {
            resp.sendRedirect("/list.jsp");
        }else {
            resp.sendRedirect("/error");
        }
    }
}
