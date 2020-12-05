package it.unipr.ingegneria.exception;

/**
 * This exception is called when a value is required but is not present
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @see Exception
 */

public class RequiredValueException extends Exception {
    String fieldRequired;

    public RequiredValueException(String fieldRequired) {
        this.fieldRequired = fieldRequired;
    }


    public String toString() {
        return "Field required " + fieldRequired;
    }

}
