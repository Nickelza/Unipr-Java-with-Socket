package it.unipr.ingegneria.api;
/**
 * The {@code Persistable} interface include the method for the persiste a generic entity on database.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */


/**
 * Method to execute the persistance on db
 */
public interface Persistable<T> {
    T persist();
}
