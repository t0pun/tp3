package org.example.tp3b;
import org.slf4j.Logger;
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
        logger.info("Méthode getID exécutée.");;
        return ID;
    }

    public String getName() {
        logger.info("Méthode getName exécutée.");;
        return name;
    }

    public int getAge() {
        logger.info("Méthode getAge exécutée.");;
        return age;
    }

    public String getEmail() {
        logger.info("Méthode getEmail exécutée.");;
        return email;
    }

    public String getPassword() {
        logger.info("Méthode getPassword exécutée.");;
        return password;
    }

    private static final Logger logger = LoggerFactory.getLogger(User.class);
}