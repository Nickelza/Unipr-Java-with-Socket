package it.unipr.ingegneria.exception;

/**
 * This exception is called when a Wine is not available
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @see Exception
 */
public class AvailabilityException extends Exception {
    public AvailabilityException() {
    }


    public String toString() {
        return "Availability Exception";
    }
}
