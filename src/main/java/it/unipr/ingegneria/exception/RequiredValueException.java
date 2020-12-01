package it.unipr.ingegneria.exception;

/**
 * Custom exception to manage a required value not provided
 *
 * @author Francesca Rossi, Everton Ejike, Ruslan Vasyunin
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
