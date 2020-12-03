package it.unipr.ingegneria.request.create;

import it.unipr.ingegneria.entities.Order;
import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.entities.user.UserFactory;
import it.unipr.ingegneria.exception.AvailabilityException;
import it.unipr.ingegneria.response.ModelResponse;
import it.unipr.ingegneria.utils.Params;
import it.unipr.ingegneria.utils.Type;

import java.util.HashMap;
import java.util.Map;

public class CreateRequestManager {

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

        return response;
    }


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

    private static Vineyard createVineyardCriteria(CreateRequest o) {
        CreateVineyardCriteria c = (CreateVineyardCriteria) ((CreateRequest<?>) o).getModel();
        Vineyard v = new Vineyard().setName(c.getName()).persist();
        return v;
    }

    private static String createProvisioningCriteria(CreateRequest o, WineShop shop) {
        CreateProvisioningCriteria c = (CreateProvisioningCriteria) ((CreateRequest<?>) o).getModel();
        Map<Params, Object> elements = new HashMap<>();
        elements.put(Params.NAME, c.getName());
        elements.put(Params.PRODUCER, c.getProducer());
        elements.put(Params.TECH_NOTES, c.getTechNotes());
        elements.put(Params.YEAR, c.getYear());
        elements.put(Params.VINEYARDS, c.getVineyards());
        elements.put(Params.QTY, c.getInQuantity());
        shop.provisionWine(elements);
        return "Started Provisioning";
    }

    private static Order createOrderCriteria(CreateRequest o, WineShop shop) {
        Order order = null;
        CreateOrderCriteria c = (CreateOrderCriteria) ((CreateRequest<?>) o).getModel();
        Map<Params, Object> elements = new HashMap<>();
        elements.put(Params.NAME, c.getName());
        elements.put(Params.QTY, c.getInQuantity());
        try {
            order = shop.sellWine(c.getUser(), elements);
        } catch (AvailabilityException e) {

        }
        return order;
    }
}
