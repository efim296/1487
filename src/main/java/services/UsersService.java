package main.java.services;

import main.java.model.entity.Users;

import java.util.List;

public interface UsersService {


    Users getUserByEmail(String email);

    Users setUserRegistration(String name, String email, String pass);

    boolean activateUser(String token);

    boolean validate(String login);

    boolean hasUser(String token);

    void saveUser(Users user);

    List<Users> getUsers();
}
