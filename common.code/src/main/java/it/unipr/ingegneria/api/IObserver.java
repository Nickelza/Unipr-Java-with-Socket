package it.unipr.ingegneria.api;

/**
 * The {@code IObserver} is use to implement the observer pattern.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public interface IObserver {
    /**
     * Method to update/notify the state
     *
     * @param o detail of notification
     */
    void update(Object o);
}
