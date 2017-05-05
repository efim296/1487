package ru.innopolis.model.entity;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersList {

    @XmlElement(name = "user")
    private List<Users> users = null;

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}