package it.unipr.ingegneria.request.create;

import it.unipr.ingegneria.request.ModelRequest;
import it.unipr.ingegneria.util.ModelRequestType;

public class CreateUserRequest extends ModelRequest<CreateUserRequest> {

    private String name;
    private String surname;
    private String email;
    private String password;


    public String getName() {
        return name;
    }

    public CreateUserRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public CreateUserRequest setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CreateUserRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CreateUserRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public CreateUserRequest asType(ModelRequestType type) {
        this.type = type.toString();
        return this;
    }
}
