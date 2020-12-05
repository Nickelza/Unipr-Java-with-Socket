package it.unipr.ingegneria.db;

/**
 * The {@code IOperations} is use to implement the common Operations in DAO
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public interface IOperations<T> {
    /**
     * Method to add a generic entity
     *
     * @param t element that observe
     */
    void add(T t);


}
