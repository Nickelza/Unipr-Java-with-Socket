package it.unipr.ingegneria.api;

import it.unipr.ingegneria.entities.user.User;

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
     * Method to remove user by the system.
     *
     * @param user User to delete
     */
    void deleteUser(User user);

    /**
     * Method to control if is user.
     *
     * @param item User to check existence
     * @return
     */
    Boolean hasUser(User item);
}
