package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.impl.WineShop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class UserDAOTest {
    private UserDAO userDAO = UserDAO.getInstance();
    private List<User> userList;
    private WineShop shop;
    private String userType;
    private String userEmail;
    private String userPassword;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userList = new ArrayList<>();
        this.shop = mock(WineShop.class);
        this.userType = new String();
        this.userEmail = new String();
        this.userPassword = new String();
    }

    @Test
    void findAllSuccess() {
        assertEquals(userList.getClass(), userDAO.findAll(shop).getClass());
    }

    @Test
    void findByTypeSuccess() {
        assertEquals(userList.getClass(), userDAO.findByType(shop, userType).getClass());
    }

    /*@Test
    void executeLoginSuccess() {
        assertEquals(User.class, userDAO.executeLogin(userEmail, userPassword, shop).getClass());
    }*/
}
