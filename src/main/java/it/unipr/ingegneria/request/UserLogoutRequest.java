package it.unipr.ingegneria.request;

import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.utils.ModelRequestType;

/**
 * The {@code UserLogoutRequest} defining the Logout request .
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class UserLogoutRequest extends ModelRequest<UserLogoutRequest> {
    private User user;


    public UserLogoutRequest() {
        super();
    }

    public User getUser() {
        return user;
    }

    public UserLogoutRequest setUser(User user) {
        this.user = user;
        return this;
    }

    public UserLogoutRequest asType(ModelRequestType type) {
        this.type = type.toString();
        return this;
    }


}
