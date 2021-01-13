package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.entities.Wine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WineDaoTest {
    private WineDAO wineDAO = WineDAO.getInstance();
    private List<Wine> wineList;
    private String wineName;
    private int wineYear;

    @BeforeEach
    public void setUp() throws Exception {
        this.wineList = new ArrayList<>();
        this.wineName = new String();
        this.wineYear = 2000;
    }

    @Test
    void findAllSuccess(){
        assertEquals(wineList.getClass(), wineDAO.findAll().getClass());
    }
}
