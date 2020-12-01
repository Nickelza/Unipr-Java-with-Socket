package it.unipr.ingegneria.exception;

/**
 * Custom exception to manage an item not available
 *
 * @author Francesca Rossi, Everton Ejike, Ruslan Vasyunin
 */
public class AvailabilityException extends Exception {
    public AvailabilityException() { }


    public String toString() {
        return "Availability Exception";
    }
}
