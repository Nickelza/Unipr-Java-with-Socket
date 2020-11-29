package it.unipr.ingegneria.entities.user;


import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.utils.Type;

import java.util.Observer;

/**
 * The {@code User} is an abstract class.
 * It is the parent class of {@code Customer} and {@code Employee}.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @see Customer
 * @see Employee
 */
public abstract class User {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Type userType;
    protected WineShop wineshop;

    public long getId() {
        return id;
    }

    public User setId(long id) {
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

    public Type getUserType() {
        return userType;
    }

    public User setUserType(Type userType) {
        this.userType = userType;
        return this;
    }

    public WineShop getWineshop() {
        return wineshop;
    }

    public User setWineshop(WineShop wineshop) {
        this.wineshop = wineshop;
        return this;
    }

    public User(String name, String surname, String email, String password, Type type) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.userType = type;
    }

    public User(String name, String surname, String email, String password, Type type, WineShop wineShop) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.userType = type;
        this.wineshop = wineShop;
    }

}
