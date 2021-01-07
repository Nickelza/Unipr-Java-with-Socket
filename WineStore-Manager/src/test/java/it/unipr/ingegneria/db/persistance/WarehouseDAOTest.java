package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.impl.CreateRequestManager;
import it.unipr.ingegneria.impl.WineShop;
import it.unipr.ingegneria.request.search.SearchRequest;
import it.unipr.ingegneria.request.search.WineSearchCriteria;
import it.unipr.ingegneria.response.ModelResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class WarehouseDAOTest {
    private String wineName;
    private Integer requiredQuantity;
    private WarehouseDAO warehouseDAO;

    @BeforeEach
    public void setUp() throws Exception {
        this.wineName = "Lambrusco";
        this.requiredQuantity = 2;
        warehouseDAO = new WarehouseDAO();
    }

    @Test
    void checkAvailabilitySuccess(){
        assertEquals(warehouseDAO.checkAvailability(wineName, requiredQuantity), Boolean.class);
    }
}
