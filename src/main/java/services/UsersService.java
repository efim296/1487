package main.java.services;
import main.java.model.entity.Users;

public interface UsersService {
 //   List<Users> findAll();

    Users getUserByEmail(String email);
    Users setUserRegistration(String name, String email, String pass);
   // Users TokenGenerator();
   // Users SendMail();
    boolean activateUser(String token);
    boolean validate(String login);
    boolean hasUser(String token);
}
