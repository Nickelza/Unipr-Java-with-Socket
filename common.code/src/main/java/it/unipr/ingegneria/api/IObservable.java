package it.unipr.ingegneria.api;

/**
 * The {@code IObservable} is use to implement the observer pattern.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public interface IObservable<T> {
    /**
     * Method to add an observer
     *
     * @param t element that observe
     */
    void addObserver(T t);

    /**
     * Method to remove an observer
     *
     * @param t element that observe
     */
    void removeObserver(T t);

}
