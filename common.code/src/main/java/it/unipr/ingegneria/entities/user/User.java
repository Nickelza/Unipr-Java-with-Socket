package it.unipr.ingegneria.entities.user;


import it.unipr.ingegneria.entities.Shop;
import it.unipr.ingegneria.utils.Type;

import java.io.Serializable;

/**
 * The {@code User} is an abstract class.
 * It is the parent class of {@code Customer} and {@code Employee}.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @see Customer
 * @see Employee
 */
public abstract class User implements Serializable {

    private int id;
    private String name;
    private String surname;
    private String email;
    private transient String password;
    private String userType;
    protected transient Shop shop;

    public User setWineshop(Shop wineshop) {
        this.shop = wineshop;
        return this;
    }


    public User() {
    }

    public User(String name, String surname, String email, String password, Type type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.userType = type.toString();
    }


    public User(int id, String name, String surname, String email, String password, Type type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.userType = type.toString();
    }


    public User(String name, String surname, String email, String password, Type type, Shop wineShop) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.userType = type.toString();
        this.shop = wineShop;
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUserType() {
        return userType;
    }

    public User setUserType(Type userType) {
        this.userType = userType.toString();
        return this;
    }

    public Shop getShop() {
        return shop;
    }
}
