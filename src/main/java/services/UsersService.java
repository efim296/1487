package main.java.services;

import main.java.model.entity.Users;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Artem Panasyuk on 19.04.2017.
 */
public interface UsersService {
    List<Users> findAll();

    Users getUserByEmail(String email);

    boolean validate(String login);
}
