package it.unipr.ingegneria.exception;
/**
 * This exception is called when something is not found
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @see java.lang.Exception
 */
public class NotFoundException extends Exception {
    public NotFoundException() { }


    public String toString() {
        return "Not Found";
    }
}
