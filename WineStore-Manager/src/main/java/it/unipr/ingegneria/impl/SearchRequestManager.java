package it.unipr.ingegneria.impl;

import it.unipr.ingegneria.DTO.OrderDTO;
import it.unipr.ingegneria.api.IStoreManager;
import it.unipr.ingegneria.api.IUserManager;
import it.unipr.ingegneria.api.IWarehouseManager;
import it.unipr.ingegneria.db.persistance.VineyardDAO;
import it.unipr.ingegneria.entities.Shop;
import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.request.search.*;
import it.unipr.ingegneria.response.ModelListResponse;
import it.unipr.ingegneria.utils.Type;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code SearchRequestManager} handle the criteria of the arrived request .
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class SearchRequestManager {
    /**
     * Handle the received request
     *
     * @param shop the WineShop
     * @param o    Object of the request
     * @return ModelListResponse containing the worked data
     */
    public static ModelListResponse fillWithResponse(Shop shop, Object o) {
        ModelListResponse response = new ModelListResponse()
                .withModels(null);

        if (((SearchRequest) o).getModel() instanceof WineSearchCriteria)
            response.withModels(createWineSearchCriteria(((SearchRequest<?>) o), shop));

        if (((SearchRequest) o).getModel() instanceof SearchVineyardCriteria)
            response.withModels(createSearchVineyardCriteria(((SearchRequest<?>) o)));

        if (((SearchRequest) o).getModel() instanceof UserSearchCriteria)
            response.withModels(createUserSearchCriteria(((SearchRequest<?>) o), shop));

        if (((SearchRequest) o).getModel() instanceof OrderSearchCriteria)
            response.withModels(createOrderSearchCriteria(((SearchRequest<?>) o), shop));

        return response;
    }

    /**
     * Handle the WineSearchCriteria object
     *
     * @param o    Object of the request
     * @param shop the WineShop
     * @return List of Wine
     */
    private static List<Wine> createWineSearchCriteria(SearchRequest o, Shop shop) {
        WineSearchCriteria u0 = (WineSearchCriteria) o.getModel();
        List<Wine> results = null;

        if (u0.getName() != null && !u0.getName().equals(""))
            results = ((IWarehouseManager) shop.getWarehouse()).findByName(u0.getName());

        if (u0.getYear() != null)
            results = ((IWarehouseManager) shop.getWarehouse()).findByYear(u0.getYear());

        if (u0.isSelectAll())
            results = ((IWarehouseManager) shop.getWarehouse()).findAll();

        return results;
    }

    /**
     * Handle the SearchVineyardCriteria object
     *
     * @param o Object of the request
     * @return List of Vineyard
     */
    private static List<Vineyard> createSearchVineyardCriteria(SearchRequest o) {
        SearchVineyardCriteria u1 = (SearchVineyardCriteria) o.getModel();
        List<Vineyard> results = null;
        if (u1.isSelectAll())
            results = VineyardDAO.getInstance().findAll();
        return results;
    }

    /**
     * Handle the UserSearchCriteria object
     *
     * @param o    Object of the request
     * @param shop the WineShop
     * @return List of User
     */
    private static List<User> createUserSearchCriteria(SearchRequest o, Shop shop) {
        UserSearchCriteria u1 = (UserSearchCriteria) o.getModel();
        List<User> results = null;
        if (u1.isMakeCountAdmin())
            results = shop.getUsers().stream().filter(user -> user.getUserType().equals(Type.ADMIN.toString())).collect(Collectors.toList());
        if (u1.isSelectAll())
            results = shop.getUsers();
        if (u1.getUserType() != null && !u1.getUserType().equals(""))
            results = ((IUserManager) shop).findByType(u1.getUserType());

        return results;
    }

    /**
     * Handle the OrderSearchCriteria object
     *
     * @param o    Object of the request
     * @param shop the WineShop
     * @return List of User
     */
    private static List<OrderDTO> createOrderSearchCriteria(SearchRequest o, Shop shop) {
        OrderSearchCriteria u1 = (OrderSearchCriteria) o.getModel();
        List<OrderDTO> results = null;
        if (u1.isSelectAll())
            results = ((IStoreManager) shop).getOrders();
        if (u1.getSearchByUser() != null)
            results = (List<OrderDTO>) (((IStoreManager) shop).getOrders()).stream()
                    .filter(item -> ((OrderDTO) item).getUserId()
                            .equals(u1.getSearchByUser().getId())).collect(Collectors.toList());
        return results;
    }
}
