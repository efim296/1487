package ru.innopolis.services;

import ru.innopolis.model.dao.UsersDao;
import ru.innopolis.model.entity.Users;
import ru.innopolis.model.impl.UsersDaoImpl;

import javax.sql.DataSource;
import java.util.List;

public class UsersServiceImpl implements UsersService {
    private DataSource dataSource = DataSourceFactory.getMyPGDataSource();
    private UsersDao usersDao = new UsersDaoImpl(dataSource);

    public List<Users> findAll() {
        return usersDao.findAll();
    }

    public Users getUserByEmail(String email) {
        return usersDao.getUserByEmail(email);
    }

    public Users setUserRegistration(String name, String email, String pass) {
        return usersDao.setUserRegistration(name, email, pass);
    }

    public boolean activateUser(String token) {
        return usersDao.activateUser(token);
    }

    public boolean validate(String email) {
        return usersDao.validate(email);
    }

    public boolean hasUser(String token) {
        return usersDao.hasUser(token);
    }

    public void saveUser(Users user) {
        usersDao.saveUser(user);
    }

    public List<Users> getUsers() {
        return usersDao.getUsers();
    }
}
