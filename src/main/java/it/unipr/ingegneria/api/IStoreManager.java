package it.unipr.ingegneria.api;

import it.unipr.ingegneria.entities.Order;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.exception.AvailabilityException;
import it.unipr.ingegneria.utils.Params;

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
    Order sellWine(User to, Map<Params, Object> elements) throws AvailabilityException;


    /**
     * Method method of replenishing wine in the warehouse
     *
     * @param elements Map that contains info about Wine as name and quantity
     */
    void provisionWine(Map<Params, Object> elements);


    /**
     * Method to send the orders at the costumers
     */
    void sendOrders();

}
