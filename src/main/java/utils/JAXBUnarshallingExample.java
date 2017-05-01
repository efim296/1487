package main.java.utils;

import main.java.model.entity.Users;
import main.java.services.UsersService;
import main.java.services.UsersServiceImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;


public class JAXBUnarshallingExample {
    public static UsersService service = new UsersServiceImpl();

    public static void main(String[] args) {
        try {
            File file = new File(System.getProperty("user.dir")
                    + File.separator + "users.xml");
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Users user = (Users) unmarshaller.unmarshal(file);
            service.saveUser(user);
            System.out.println(user);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }
}
