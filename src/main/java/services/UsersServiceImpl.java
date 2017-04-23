package main.java.services;

import main.java.model.dao.UsersDao;
import main.java.model.entity.Users;
import main.java.model.impl.UsersDaoImpl;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Artem Panasyuk on 19.04.2017.
 */
public class UsersServiceImpl implements UsersService {
    private DataSource dataSource = DataSourceFactory.getMyPGDataSource();
    private UsersDao usersDao = new UsersDaoImpl(dataSource);

    public List<Users> findAll(){
      return usersDao.findAll();
    }

    public Users getUserByEmail(String email){
      return usersDao.getUserByEmail(email);
    }

    public boolean validate(String email){
        return usersDao.validate(email);
    }
}
