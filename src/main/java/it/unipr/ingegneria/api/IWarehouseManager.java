package it.unipr.ingegneria.api;

import java.util.List;
/**
 * The {@code IWarehouseManager} is a generic interface that include the principal service for the Warehouse
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @param <T> Generic Parameter
 */
public interface IWarehouseManager<T> {
     /**
      * Method to search a generic element by name.
      * @param name name of searched element
      * @return a generic list
      */
     List<T> findByName(String name);

     /**
      * Method to search a generic element by year.
      * @param d year of searched element
      * @return
      */
     List<T> findByYear(int d);
}
