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
import java.util.List;

/**
 * Created by admin on 18.04.2017.
 */
public class ListController extends HttpServlet {

    public  static UsersService service = new UsersServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("value", "Hello,student");

/*        List<Student> list = new ArrayList<>();
        Student student1 = new Student(1,"Vasya", 12);
        Student student2 = new Student(2,"Kolya", 22);
        list.add(student1);
        list.add(student2);*/
        List<Users> list = service.findAll();
        req.setAttribute("list", list);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/list.jsp");
        dispatcher.forward(req, resp);
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
