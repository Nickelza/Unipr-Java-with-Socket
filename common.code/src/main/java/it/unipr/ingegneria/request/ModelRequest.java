package it.unipr.ingegneria.request;

import it.unipr.ingegneria.utils.ModelRequestType;

import java.io.Serializable;

/**
 * The {@code ModelRequest} is a wrapper for defining the request type .
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public abstract class ModelRequest<T> implements Serializable {
    protected String type;

    public abstract T asType(ModelRequestType type);

    public String getType() {
        return type;
    }


}
