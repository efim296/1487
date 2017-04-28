package main.java.controllers;

import main.java.model.entity.Users;
import main.java.services.UsersService;
import main.java.services.UsersServiceImpl;

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
        boolean validEmail = service.validate(login);

        if (!validEmail) {
            req.setAttribute("error", "error");
            dispatcher.forward(req, resp);
        } else {
            Users user = service.getUserByEmail(login);
            if (password.equals(user.getPass()) && (user.getVerification())) {

                HttpSession session = req.getSession();
                session.setAttribute("token", user.getToken());

                resp.sendRedirect("/z/list");
            } else {
                req.setAttribute("error", "error");
                dispatcher.forward(req, resp);
            }
        }
    }
}
