package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.entities.Vineyard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VineyardDAOTest {
    private VineyardDAO vineyardDAO = VineyardDAO.getInstance();
    private List<Vineyard> vineyardList;

    @BeforeEach
    public void setUp() throws Exception {
        vineyardList = new ArrayList<>();
    }

    @Test
    void findAllSuccess() {
        assertEquals(vineyardList.getClass(), vineyardDAO.findAll().getClass());
    }
}
