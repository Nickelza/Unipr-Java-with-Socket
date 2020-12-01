package it.unipr.ingegneria.entities.user;


import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.utils.Type;

import java.util.Observer;

/**
 *  The {@code User} is an abstract class.
 *  It is the father class of {@code Customer} and {@code Employee}.
 *
 * @author Francesca Rossi, Everton Ejike, Ruslan Vasyunin
 * @see Customer
 * @see Employee
 */
public abstract class User {

    /**
     * User unique ID
     */
    private long id;
    /**
     * User name
     */
    private String name;
    /**
     * User surname
     */
    private String surname;
    /**
     * User email
     */
    private String email;
    /**
     * User password
     */
    private String password;
    /**
     * User Type
     * @see Type
     */
    private Type userType;
    /**
     * An instance of {@code WineShop}
     * @see WineShop
     */
    private WineShop wineshop;

    /**
     * Default class constructor
     * @param _id
     * @param name
     * @param surname
     * @param email
     * @param password
     * @param type
     */
    public User(long _id, String name, String surname, String email, String password, Type type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.userType = type;
    }

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
}
