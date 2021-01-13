package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.DTO.OrderDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderDAOTest {
    private OrderDAO orderDAO = OrderDAO.getInstance();
    private List<OrderDTO> orderDTOList;

    @BeforeEach
    public void setUp() throws Exception {
        orderDTOList = new ArrayList<>();
    }

    @Test
    void findAllSuccess(){
        assertEquals(orderDTOList.getClass(), orderDAO.findAll().getClass());
    }
}
