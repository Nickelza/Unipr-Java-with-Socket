package it.unipr.ingegneria.request.search;

import it.unipr.ingegneria.db.DTO.OrderDTO;
import it.unipr.ingegneria.db.persistance.VineyardDAO;
import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.response.ModelListResponse;
import it.unipr.ingegneria.utils.Type;

import java.util.List;
import java.util.stream.Collectors;

public class SearchRequestManager {
    public static ModelListResponse fillWithResponse(WineShop shop, Object o) {
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

    private static List<Wine> createWineSearchCriteria(SearchRequest o, WineShop shop) {
        WineSearchCriteria u0 = (WineSearchCriteria) o.getModel();
        List<Wine> results = null;

        if (u0.getName() != null && !u0.getName().equals(""))
            results = shop.getWarehouse().findByName(u0.getName());

        if (u0.getYear() != null)
            results = shop.getWarehouse().findByYear(u0.getYear());

        if (u0.isSelectAll())
            results = shop.getWarehouse().findAll();

        return results;
    }

    private static List<Vineyard> createSearchVineyardCriteria(SearchRequest o) {
        SearchVineyardCriteria u1 = (SearchVineyardCriteria) o.getModel();
        List<Vineyard> results = null;
        if (u1.isSelectAll())
            results = VineyardDAO.getInstance().findAll();
        return results;
    }

    private static List<User> createUserSearchCriteria(SearchRequest o, WineShop shop) {
        UserSearchCriteria u1 = (UserSearchCriteria) o.getModel();
        List<User> results = null;
        if (u1.isMakeCountAdmin())
            results = shop.getUsers().stream().filter(user -> user.getUserType().equals(Type.ADMIN)).collect(Collectors.toList());
        if (u1.isSelectAll())
            results = shop.getUsers();
        if (u1.getUserType() != null && !u1.getUserType().equals(""))
            results = shop.findByType(u1.getUserType());

        return results;
    }

    private static List<OrderDTO> createOrderSearchCriteria(SearchRequest o, WineShop shop) {
        OrderSearchCriteria u1 = (OrderSearchCriteria) o.getModel();
        List<OrderDTO> results = null;
        if (u1.isSelectAll())
            results = shop.getOrders();
        if (u1.getSearchByUser() != null)
            results = shop.getOrders().stream().filter(item -> item.getUserId().equals(u1.getSearchByUser().getId())).collect(Collectors.toList());
        return results;
    }
}
