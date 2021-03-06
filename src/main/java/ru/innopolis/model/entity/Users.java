package ru.innopolis.model.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Users {
    private int id;
    private String name;
    private String email;
    private String token;
    private boolean verification;
    private String pass;

    public Users() {
    }

    public Users(int id, String name, String email, String token, boolean verification, String pass) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.token = token;
        this.verification = verification;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    @XmlElement
    public void setToken(String token) {
        this.token = token;
    }

    public boolean getVerification() {
        return verification;
    }

    @XmlElement
    public void setVerification(boolean verification) {
        this.verification = verification;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email=" + email +
                ", token=" + token +
                ", verification=" + verification +
                ", pass=" + pass +

                '}';
    }
}
