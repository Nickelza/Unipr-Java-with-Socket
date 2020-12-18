package it.unipr.ingegneria.impl;

import it.unipr.ingegneria.db.persistance.VineyardDAO;
import it.unipr.ingegneria.entities.Order;
import it.unipr.ingegneria.entities.Vineyard;

import it.unipr.ingegneria.entities.user.Customer;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.exception.AvailabilityException;
import it.unipr.ingegneria.request.create.*;
import it.unipr.ingegneria.response.ModelResponse;
import it.unipr.ingegneria.utils.Params;
import it.unipr.ingegneria.utils.Type;
import it.unipr.ingegneria.utils.UserFactory;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code CreateRequestManager} handle the criteria of the arrived request .
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class CreateRequestManager {
    private static final Logger logger = Logger.getLogger(WineShop.class);

    /**
     * Handle the received request
     *
     * @param shop the WineShop
     * @param o    Object of the request
     * @return ModelResponse containing the worked data
     */
    public static ModelResponse fillWithResponse(WineShop shop, Object o) {
        ModelResponse response = new ModelResponse<User>().withModel(null);

        if (((CreateRequest<?>) o).getModel() instanceof CreateUserCriteria)
            response.withModel(createUserCriteria(((CreateRequest<?>) o), shop));

        if (((CreateRequest<?>) o).getModel() instanceof CreateVineyardCriteria)
            response.withModel(createVineyardCriteria(((CreateRequest<?>) o)));

        if (((CreateRequest<?>) o).getModel() instanceof CreateProvisioningCriteria)
            response.withModel(createProvisioningCriteria(((CreateRequest<?>) o), shop));

        if (((CreateRequest<?>) o).getModel() instanceof CreateOrderCriteria)
            response.withModel(createOrderCriteria(((CreateRequest<?>) o), shop));

        if (((CreateRequest<?>) o).getModel() instanceof CreateSendOrderCriteria)
            response.withModel(createSendOrderCriteria(((CreateRequest<?>) o), shop));

        return response;
    }

    /**
     * Handle the CreateUserCriteria object
     *
     * @param shop the WineShop
     * @param o    Object of the request
     * @return User created
     */
    private static User createUserCriteria(CreateRequest o, WineShop shop) {
        CreateUserCriteria c = (CreateUserCriteria) ((CreateRequest<?>) o).getModel();
        User u = UserFactory.getUser(Type.valueOf(c.getUserType()));
        if (u != null) {
            u.setEmail(c.getEmail())
                    .setName(c.getName())
                    .setSurname(c.getSurname())
                    .setPassword(c.getPassword())
                    .setUserType(Type.valueOf(c.getUserType()))
                    .setWineshop(shop);
            shop.addUser(u);
        }
        return u;
    }

    /**
     * Handle the CreateVineyardCriteria object
     *
     * @param o Object of the request
     * @return Vineyard created
     */
    private static Vineyard createVineyardCriteria(CreateRequest o) {
        CreateVineyardCriteria c = (CreateVineyardCriteria) ((CreateRequest<?>) o).getModel();
        Vineyard vineyard = new Vineyard().setName(c.getName());
        VineyardDAO.getInstance().add(vineyard);
        return vineyard;
    }

    /**
     * Handle the CreateProvisioningCriteria object
     *
     * @param o Object of the request
     * @return a default message string
     */
    private static String createProvisioningCriteria(CreateRequest o, WineShop shop) {
        CreateProvisioningCriteria c = (CreateProvisioningCriteria) ((CreateRequest<?>) o).getModel();
        Map<Params, Object> elements = new HashMap<>();
        elements.put(Params.NAME, c.getName());
        elements.put(Params.PRODUCER, c.getProducer());
        elements.put(Params.TECH_NOTES, c.getTechNotes());
        elements.put(Params.YEAR, c.getYear());
        elements.put(Params.VINEYARDS, c.getVineyards());
        elements.put(Params.QTY, c.getInQuantity());

        shop.restock(elements);
        return "Started Provisioning";
    }

    /**
     * Handle the CreateOrderCriteria object
     *
     * @param o Object of the request
     * @return Order created
     */
    private static Order createOrderCriteria(CreateRequest o, WineShop shop) {
        Order order = null;
        CreateOrderCriteria c = (CreateOrderCriteria) ((CreateRequest<?>) o).getModel();
        Customer customer = (Customer) c.getUser();
        Map<Params, Object> elements = new HashMap<>();
        elements.put(Params.NAME, c.getName());
        elements.put(Params.QTY, c.getInQuantity());
        try {
            order = shop.sell(c.getUser(), elements);
            customer.getOrders().add(order);
        } catch (AvailabilityException e) {
            logger.error(e);
        }
        return order;
    }

    /**
     * Handle the CreateSendOrderCriteria object
     *
     * @param o Object of the request
     * @return a default message string
     */
    private static String createSendOrderCriteria(CreateRequest o, WineShop shop) {
        CreateSendOrderCriteria c = (CreateSendOrderCriteria) ((CreateRequest<?>) o).getModel();
        shop.sendOrders();
        return "Orderd Sended";
    }
}
