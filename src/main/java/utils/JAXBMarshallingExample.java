package main.java.utils;

import main.java.model.entity.Users;
import main.java.model.entity.UsersList;
import main.java.services.UsersService;
import main.java.services.UsersServiceImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;


public class JAXBMarshallingExample {
    static UsersList users = new UsersList();
    static UsersService service = new UsersServiceImpl();

    public static void main(String[] args) {
        users.setUsers(service.getUsers());

        try {
            File file = new File(System.getProperty("user.dir")
                    + File.separator + "users.xml");
            JAXBContext context = JAXBContext.newInstance(UsersList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(users, file);
            marshaller.marshal(users, System.out);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }
}
