package it.unipr.ingegneria.impl;

import it.unipr.ingegneria.entities.Shop;
import it.unipr.ingegneria.request.create.CreateRequest;
import it.unipr.ingegneria.request.create.CreateUserCriteria;
import it.unipr.ingegneria.response.ModelResponse;
import it.unipr.ingegneria.utils.Type;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class CreateRequestManagerTest {
    private WineShop shop;
    private CreateRequest o;
    private CreateUserCriteria userTestCriteria;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.shop=mock(WineShop.class);
        this.o=mock(CreateRequest.class);
        userTestCriteria=new CreateUserCriteria()
                .setUserType(Type.CLIENT)
                .setEmail("prova@gmail.com")
                .setName("pippo")
                .setPassword("pippo1234")
                .setSurname("pluto");

    }

    @Test
    void ShouldFillWithResponseSuccess() {
        when(o.getModel()).thenReturn(userTestCriteria);
        ModelResponse response=CreateRequestManager.fillWithResponse(shop,  o);
        assertEquals( ModelResponse.class, response.getClass());
    }
}