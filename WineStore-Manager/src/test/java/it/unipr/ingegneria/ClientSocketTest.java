package it.unipr.ingegneria;

import it.unipr.ingegneria.entities.Order;
import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.entities.user.Customer;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.request.UserLoginRequest;
import it.unipr.ingegneria.request.create.CreateOrderCriteria;
import it.unipr.ingegneria.request.create.CreateProvisioningCriteria;
import it.unipr.ingegneria.request.create.CreateUserCriteria;
import it.unipr.ingegneria.utils.ModelRequestType;
import it.unipr.ingegneria.utils.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ClientSocketTest {
    private  ClientSocket client;
    private CreateUserCriteria userTestCriteria;
    private CreateProvisioningCriteria wineTestCriteria;
    @BeforeEach
    void setUp() {
        this.client=new ClientSocket();
        userTestCriteria=new CreateUserCriteria()
                .setUserType(Type.CLIENT)
                .setEmail("prova@gmail.com")
                .setName("pippo")
                .setPassword("pippo1234")
                .setSurname("pluto");
        wineTestCriteria=new CreateProvisioningCriteria()
                .setInQuantity(10)
                .setName("Vino 1")
                .setProducer("pluto")
                .setTechNotes("")
                .setVineyard(mock(Vineyard.class))
                .setYear(2010);


    }

    @Test
    void ShouldcreateUserSuccess() {

        assertEquals( Customer.class, client.createUser(userTestCriteria).getClass());
    }

   @Test
    void ShouldloginUserSuccess() {
       this.client.createUser(this.userTestCriteria);
       UserLoginRequest user=new UserLoginRequest()
                .setEmail("prova@gmail.com")
                .setPassword("pippo1234")
                .asType(ModelRequestType.LOGIN);
       assertEquals( Customer.class, client.loginUser(user).getClass());

    }

    @Test
    void logoutUser() {
    }

    @Test
    void createOrder() {
        String wine=this.client.createProvisioning(this.wineTestCriteria);
        CreateOrderCriteria order=new CreateOrderCriteria()
                .setInQuantity(5)
                .setName("Vino 1")
                .setUser(mock(Customer.class));
        assertEquals(Order.class, client.createOrder(order).getClass() );

    }
}