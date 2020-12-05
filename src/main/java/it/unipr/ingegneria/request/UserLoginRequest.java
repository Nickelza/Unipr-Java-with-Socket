package it.unipr.ingegneria.request;

import it.unipr.ingegneria.utils.ModelRequestType;

/**
 * The {@code UserLoginRequest} defining the Login request .
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class UserLoginRequest extends ModelRequest<UserLoginRequest> {
    private String email;
    private String password;


    public UserLoginRequest() {
        super();
    }


    public String getEmail() {
        return email;
    }

    public UserLoginRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserLoginRequest asType(ModelRequestType type) {
        this.type = type.toString();
        return this;
    }


}
