package main.java.model.dao;

import main.java.model.entity.Users;

import java.util.List;

public interface UsersDao {
    List<Users> findAll();

    Users findById(long id);

    int insert(Users users);

    int delete(long id);

    int update(Users users);


    Users getUserByEmail(String email);

     boolean validate(String email);



}
