package it.unipr.ingegneria.api;

import it.unipr.ingegneria.entities.user.User;

public interface IUserManager {
    void addUser(User user);
    void deleteUser(User user);
    Boolean hasUser(User item);
}
