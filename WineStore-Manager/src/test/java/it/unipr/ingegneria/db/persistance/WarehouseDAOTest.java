package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.entities.Wine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WarehouseDAOTest {
    private WarehouseDAO warehouseDAO = WarehouseDAO.getInstance();
    private List<Wine> wineList;
    private String wineName;
    private Integer requiredQuantity;

    @BeforeEach
    public void setUp() throws Exception {
        this.wineList = new ArrayList<>();
        this.wineName = new String();
        this.requiredQuantity = 2000;
    }

    @Test
    void findAllSuccess(){
        assertEquals(wineList.getClass(), warehouseDAO.findAll().getClass());
    }

    @Test
    void findWineNotInWarehouseSuccess(){
        List<String> response = new ArrayList<>();
        assertEquals(response.getClass(), warehouseDAO.findWineNotInWarehouse().getClass());
    }

    @Test
    void checkAvailabilitySuccess(){
        assertEquals(Boolean.class, warehouseDAO.checkAvailability(wineName, requiredQuantity).getClass());
    }
}
