package it.unipr.ingegneria.exception;

/**
 * Custom exception to manage an item not found
 *
 * @author Francesca Rossi, Everton Ejike, Ruslan Vasyunin
 */
public class NotFoundException extends Exception {
    public NotFoundException() { }


    public String toString() {
        return "Not Found";
    }
}
