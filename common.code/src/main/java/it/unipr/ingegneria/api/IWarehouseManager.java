package it.unipr.ingegneria.api;

import java.util.List;

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
}
