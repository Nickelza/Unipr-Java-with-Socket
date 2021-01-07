package it.unipr.ingegneria;

import it.unipr.ingegneria.entities.user.Customer;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.request.UserLoginRequest;
import it.unipr.ingegneria.request.create.CreateUserCriteria;
import it.unipr.ingegneria.utils.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ClientSocketTest {
    private  ClientSocket client;
    private CreateUserCriteria userTestCriteria;
    @BeforeEach
    void setUp() {
        this.client=new ClientSocket();

    }

    @Test
    void ShouldcreateUserSuccess() {
        userTestCriteria=new CreateUserCriteria()
                .setUserType(Type.CLIENT)
                .setEmail("prova@gmail.com")
                .setName("pippo")
                .setPassword("pippo1234")
                .setSurname("pluto");
        assertEquals( Customer.class, client.createUser(userTestCriteria).getClass());
    }

   @Test
    void loginUser() {
        UserLoginRequest user=new UserLoginRequest()
                .setEmail("prova@gmail.com")
                .setPassword("pippo1234");
        assertEquals( User.class, client.loginUser(mock(UserLoginRequest.class)).getClass());

    }

    @Test
    void logoutUser() {
    }

    @Test
    void createOrder() {
    }
}