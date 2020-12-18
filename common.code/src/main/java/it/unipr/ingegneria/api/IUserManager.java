package it.unipr.ingegneria.api;

import it.unipr.ingegneria.entities.user.User;
import java.util.List;

/**
 * The {@code IUserManager} interface contain the method to manage a user.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public interface IUserManager {
    /**
     * Method to add user in the system.
     *
     * @param user User to add
     */
    void addUser(User user);

    /**
     * Method to control if is user.
     *
     * @param email    User email
     * @param password User password
     * @return User if exisist
     */
    User findByMailAndPassword(String email, String password);

    List<User> findByType(String userType);

}
