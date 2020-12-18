package it.unipr.ingegneria.api;

import it.unipr.ingegneria.DTO.OrderDTO;
import it.unipr.ingegneria.entities.Order;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.exception.AvailabilityException;
import it.unipr.ingegneria.utils.Params;

import java.util.List;
import java.util.Map;

/**
 * The {@code IStoreManager}  extends the {@code IWarehouseManager} adding additional behaviour for manage the elements.
 *
 * @param <T> Generic Parameter
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @see IWarehouseManager
 * @see it.unipr.ingegneria.exception.AvailabilityException
 */
public interface IStoreManager<T> {
    /**
     * Method that sell the wine
     *
     * @param elements Map that contains info about Wine as name and quantity
     * @return a generic list
     * @throws AvailabilityException
     */
    Order sell(User to, Map<Params, Object> elements) throws AvailabilityException;


    /**
     * Method for restock wine in the warehouse
     *
     * @param elements Map that contains info about Wine as name and quantity
     */
    void restock(Map<Params, Object> elements);


    /**
     * Method to send the orders at the costumers
     */
    void sendOrders();

    /**
     * Find all executed orders
     *
     * @return List of OrderDTO
     */
    List<OrderDTO> getOrders();
}
