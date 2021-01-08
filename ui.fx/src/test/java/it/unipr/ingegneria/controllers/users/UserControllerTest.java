package it.unipr.ingegneria.controllers.users;

import it.unipr.ingegneria.ClientSocket;
import it.unipr.ingegneria.request.create.CreateUserCriteria;
import it.unipr.ingegneria.utils.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    private UserController userController;
    @BeforeEach
    void setUp() {
        this.userController=new UserController();
    }
    @Test
    void ShouldcreateUserSuccess() {
        String name="pippo";
        String surname="rossi";
        String email="prova@gmail.com";
        String psw="1234";
        Type type=Type.CLIENT;
        assertEquals(CreateUserCriteria.class, userController.createUser(name, surname, email, psw, type).getClass());
    }
}