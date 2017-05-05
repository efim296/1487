package ru.innopolis.utils;

import ru.innopolis.model.entity.Users;
import ru.innopolis.services.UsersService;
import ru.innopolis.services.UsersServiceImpl;

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
