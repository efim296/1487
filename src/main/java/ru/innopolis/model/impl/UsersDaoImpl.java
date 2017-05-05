package ru.innopolis.model.impl;

import ru.innopolis.model.dao.UsersDao;
import ru.innopolis.model.entity.Users;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsersDaoImpl implements UsersDao {

    private static final Logger LOG = Logger.getLogger(UsersDaoImpl.class);
    private DataSource dataSource;
    private static Pattern pattern;
    private static Matcher matcher;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/lab";
    private static final String DB_USER = "r2d2";
    private static final String DB_PASS = "";

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
                list.add(users);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
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
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, email, pass) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, users.getName());
            preparedStatement.setString(2, users.getEmail());
            preparedStatement.setString(3, users.getPass());
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
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET name = ?, email = ?, pass = ? WHERE id = ?");
            preparedStatement.setString(1, users.getName());
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
                    DB_URL, "r2d2", "");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email=" + "email");
            result = preparedStatement.executeQuery();

            if (result.next()) {
                user.setId(result.getInt("id"));
                user.setEmail(result.getString("email"));
                user.setPass(result.getString("pass"));
                user.setToken(result.getString("token"));
                user.setVerification(result.getBoolean("verification"));
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

    public boolean validate(final String hex) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }

    int resultSet;

    public Users setUserRegistration(String name, String email, String pass) {
        Users userreg = new Users();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = null;
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String token = this.TokenGenerator();

            Mailer.instance().sendMessage(email, token);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(name, email, token, pass) VALUES ('" + name + "', '" + email + "','" + token + "', '" + pass + "')");
            resultSet = preparedStatement.executeUpdate();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userreg;


    }

    public String TokenGenerator() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
    }

    public boolean activateUser(String token) {
        Users activuser = new Users();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = null;
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET verification = TRUE WHERE token = '" + token + "'");
            resultSet = preparedStatement.executeUpdate();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet == 0 ? false : true;

    }

    public boolean hasUser(String token) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE token= '" + token + "' AND verification= TRUE ");
            resultSet = preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet == 0 ? false : true;

    }

    public void saveUser(Users user) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String token = this.TokenGenerator();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(name, email, token, pass) VALUES ('" + user.getName() + "', '" + user.getEmail() + "','" + token + "', '" + user.getPass() + "')");
            resultSet = preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public List<Users> getUsers() {
        List<Users> usersList = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Users user = new Users();
                user.setId(result.getInt(1));
                user.setName(result.getString(2));
                user.setEmail(result.getString(3));
                user.setToken(result.getString(4));
                user.setVerification(result.getBoolean(5));
                user.setPass(result.getString(6));
                usersList.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usersList;
    }


}