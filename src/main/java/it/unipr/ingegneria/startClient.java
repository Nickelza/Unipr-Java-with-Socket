package it.unipr.ingegneria;

import it.unipr.ingegneria.db.DTO.OrderDTO;
import it.unipr.ingegneria.entities.Order;
import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.entities.user.Customer;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.request.UserLoginRequest;
import it.unipr.ingegneria.request.UserLogoutRequest;
import it.unipr.ingegneria.request.create.CreateOrderCriteria;
import it.unipr.ingegneria.request.create.CreateProvisioningCriteria;
import it.unipr.ingegneria.request.create.CreateUserCriteria;
import it.unipr.ingegneria.request.create.CreateVineyardCriteria;
import it.unipr.ingegneria.request.search.OrderSearchCriteria;
import it.unipr.ingegneria.request.search.SearchVineyardCriteria;
import it.unipr.ingegneria.request.search.UserSearchCriteria;
import it.unipr.ingegneria.request.search.WineSearchCriteria;
import it.unipr.ingegneria.utils.ModelRequestType;
import it.unipr.ingegneria.utils.Type;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class startClient {
    public static void main(final String[] args) throws IOException, SQLException {

        // Parte di esempio da integrare in JavaFX
        ClientSocket clientSocket = new ClientSocket();

        CreateUserCriteria createUserCriteria = new CreateUserCriteria()
                .setName("Mario")
                .setSurname("Rossi")
                .setEmail("mario.rossi@gmail.com")
                .setPassword("P@ssw0rd")
                .setUserType(Type.CLIENT);

        Customer user = (Customer) clientSocket.createUser(createUserCriteria);


        CreateVineyardCriteria createVineyardCriteria = new CreateVineyardCriteria().setName("Vigna");
        Vineyard vineyard = clientSocket.createVineyard(createVineyardCriteria);

        CreateProvisioningCriteria createProvisioningCriteriaLambrusco =
                new CreateProvisioningCriteria().setName("Lambrusco")
                        .setProducer("Producer")
                        .setTechNotes("TECH_NOTES")
                        .setYear(2015)
                        .setInQuantity(25)
                        .setVineyard(vineyard);
        String results = clientSocket.createProvisioning(createProvisioningCriteriaLambrusco);


        CreateOrderCriteria createOrderLambruscoCriteria = new CreateOrderCriteria()
                .setInQuantity(30)
                .setName("Lambrusco")
                .setUser(user);

        Order o = clientSocket.createOrder(createOrderLambruscoCriteria);


        CreateUserCriteria createEmployeeCriteria = new CreateUserCriteria()
                .setName("Lucia")
                .setSurname("Neri")
                .setEmail("lucia.neri@gmail.com")
                .setPassword("P@ssw0rd")
                .setUserType(Type.EMPLOYEE);

        clientSocket.createUser(createEmployeeCriteria);

        UserLoginRequest userLoginRequest = new UserLoginRequest().asType(ModelRequestType.LOGIN).setEmail("mario.rossi@gmail.com").setPassword("P@ssw0rd");
        User customerCreated = clientSocket.loginUser(userLoginRequest);


        CreateProvisioningCriteria createProvisioningCriteriaChianti =
                new CreateProvisioningCriteria().setName("Chianti")
                        .setProducer("Chianti")
                        .setTechNotes("TECH_NOTES")
                        .setYear(2012)
                        .setInQuantity(25)
                        .setVineyard(vineyard);


        clientSocket.createProvisioning(createProvisioningCriteriaChianti);


        CreateOrderCriteria createOrderChiantiCriteria = new CreateOrderCriteria()
                .setInQuantity(25)
                .setName("Chianti")
                .setUser(user);

        clientSocket.createOrder(createOrderChiantiCriteria);

        // Search all wines in Warehouse
        WineSearchCriteria searchAllWinesCriteria = new WineSearchCriteria().setSelectAll(true);
        List<Wine> allWines = clientSocket.searchWines(searchAllWinesCriteria);

        // Search wines byName
        WineSearchCriteria searchAllWinesCriteriaByName = new WineSearchCriteria().setName("Chianti");
        List<Wine> winesByName = clientSocket.searchWines(searchAllWinesCriteria);

        // Search wines by Year
        WineSearchCriteria searchAllWinesCriteriaByYear = new WineSearchCriteria().setYear(2012);
        List<Wine> winesByYear = clientSocket.searchWines(searchAllWinesCriteria);

        SearchVineyardCriteria searchVineyardCriteria = new SearchVineyardCriteria().setSelectAll(true);
        List<Vineyard> vineyards = clientSocket.searchVineyards(searchVineyardCriteria);

        // Search All Users
        UserSearchCriteria userSearchCriteria = new UserSearchCriteria().setSelectAll(true);
        List<User> users = clientSocket.searchUsers(userSearchCriteria);

        UserSearchCriteria userSearchCriteriaByType = new UserSearchCriteria().setUserType(Type.CLIENT);
        List<User> usersTypes = clientSocket.searchUsers(userSearchCriteriaByType);

        OrderSearchCriteria orderSearchCriteria = new OrderSearchCriteria().setSearchByUser(user);
        List<OrderDTO> orderDTOS = clientSocket.searchOrders(orderSearchCriteria);


        UserLogoutRequest userLogoutRequest = new UserLogoutRequest().setUser(user).asType(ModelRequestType.LOGOUT);
        clientSocket.logoutUser(userLogoutRequest);
        clientSocket.closeSocket();

    }
}
