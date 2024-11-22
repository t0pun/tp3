package org.example.tp3b;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class User {

    private int ID;
    private String name;
    private int age;
    private String email;
    private String password;


    public User(int ID, String name, int age, String email, String password) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }



}
