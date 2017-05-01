package main;

import main.java.model.dao.GroupDao;
import main.java.model.dao.UsersDao;
import main.java.model.entity.Group;
import main.java.model.entity.Users;
import main.java.model.impl.GroupDaoImpl;
import main.java.model.impl.UsersDaoImpl;
import main.java.services.DataSourceFactory;

import javax.sql.DataSource;

public class Main {
    private static UsersDao usersDao;
    private static GroupDao groupDao;

    public static void main(String[] args) {
        DataSource dataSource = DataSourceFactory.getMyPGDataSource();
        groupDao = new GroupDaoImpl(dataSource);
        usersDao = new UsersDaoImpl(dataSource);

        for (Group group : groupDao.findAll()) {
            System.out.println(group);
            System.out.println();
        }
        for (Users users : usersDao.findAll()) {
            System.out.println(users);
        }
        System.out.println();
    }
}
