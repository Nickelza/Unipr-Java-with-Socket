package it.unipr.ingegneria.api;

import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.exception.AvailabilityException;
import it.unipr.ingegneria.exception.RequiredValueException;
import it.unipr.ingegneria.utils.Params;

import java.util.List;
import java.util.Map;

/**
 * The {@code IWarehouseManager} is a generic interface that include the principal service for the Warehouse
 *
 * @param <T> Generic Parameter
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public interface IWarehouseManager<T> {
    /**
     * Method to search a generic element by name.
     *
     * @param name name of searched element
     * @return a generic list of elements filtered by Name
     */
    List<T> findByName(String name);

    /**
     * Method to search a generic element by year.
     *
     * @param d year of searched element
     * @return a generic list of elements filtered by Year
     */
    List<T> findByYear(int d);

    /**
     * Method to search all elements.
     *
     * @return generic list of elements not filtered
     */
    List<T> findAll();

    /**
     * Method to search all elements available in current warehouse.
     *
     * @return generic list of elements not filtered
     */
    List<T> findAllAvailables();

    /**
     * Method to add elements in current warehouse.
     *
     * @param elements map containins info
     */
    public void add(Map<Params, Object> elements) throws RequiredValueException;

    List<T> remove(Map<Params, Object> elements) throws RequiredValueException, AvailabilityException;

}
