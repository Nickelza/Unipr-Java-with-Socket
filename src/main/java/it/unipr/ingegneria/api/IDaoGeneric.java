package it.unipr.ingegneria.api;

import java.sql.SQLException;
import java.util.List;

/**
 * The {@code IDaoGeneric} is Data Access Obejct is used to extend the classes which will implement methods to access the Databse
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public interface IDaoGeneric<T> {

    /**
     * Method to add a generic item
     * @param t
     * @return id of the added object
     */
    int add  (T t) throws SQLException;;

    /**
     * Method to delete an item
     * @param id Unique id of the searched element
     * @return
     */
    boolean delete(int id) throws SQLException;

    /**
     * Updates an item
     * @param t
     * @return success status
     */
    boolean update(T t) throws SQLException;

    /**
     * Method to get all elements of an item
     * @return a generic list of items
     */
    List<T> findAll() throws SQLException;

    /**
     * Method to get an item provided it's id
     * @param id Unique id of the searched item
     * @return a generic item
     */
    <T> T findById(int id) throws SQLException;

    /**
     * Method to get an item provided it's name
     * @param name Non unique name of the requested item
     * @return a generic list of items
     */
    List<T> findByName(String name) throws SQLException;
}