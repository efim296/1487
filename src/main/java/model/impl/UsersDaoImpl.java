package main.java.model.impl;

import main.java.model.dao.UsersDao;
import main.java.model.entity.Users;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsersDaoImpl implements UsersDao {

    private static final Logger LOG = Logger.getLogger(UsersDaoImpl.class);
    private DataSource dataSource;
    private static Pattern pattern;
    private static Matcher matcher;


    public UsersDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Users> findAll() {
        List<Users> list = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                Users users = new Users();
                users.setId(resultSet.getInt(1));
                users.setName(resultSet.getString(2));
                users.setEmail(resultSet.getString(3));
//                users.setGroupId(resultSet.getLong(4));
//                users.setGroup(new GroupDaoImpl(dataSource).findById(users.getGroupId()));
                list.add(users);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (
                SQLException e)

        {
            e.printStackTrace();
        }
        return list;
    }

    public Users findById(long id) {
        Users users = null;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                users = new Users();
                users.setId(resultSet.getInt(1));
                users.setName(resultSet.getString(2));
                users.setEmail(resultSet.getString(3));
//                users.setGroupId(resultSet.getLong(4));
//                users.setGroup(new GroupDaoImpl(dataSource).findById(users.getGroupId()));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public int insert(Users users) {
        int lastId = 0;

        if (users == null)
            return lastId;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, age, group_id) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, users.getName());
            preparedStatement.setString(2, users.getEmail());
//            preparedStatement.setLong(3, users.getGroupId());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) lastId = rs.getInt(1);
            rs.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastId;
    }

    public int delete(long id) {
        int result = 0;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Users users) {
        int result = 0;

        if (users == null)
            return result;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET name = ?, age = ?, group_id = ? WHERE id = ?");
            preparedStatement.setString(1, users.getName());
//            preparedStatement.setInt(2, users.getAge());
//            preparedStatement.setLong(3, users.getGroupId());
            preparedStatement.setLong(4, users.getId());

            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    ResultSet result;

    public Users getUserByEmail(String email) {
        Users user = new Users();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5433/lab", "postgres", "yecgaa");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email=" + "email");
            result = preparedStatement.executeQuery();

            if (result.next()) {
                user.setEmail(result.getString("email"));
                user.setPass(result.getString("pass"));


            }
            preparedStatement.close();


            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }






        private static final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public  boolean validate(final String hex)
        {
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(hex);
            return matcher.matches();
        }

    }





