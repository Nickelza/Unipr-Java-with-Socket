package it.unipr.ingegneria.request;

import it.unipr.ingegneria.util.ModelRequestType;

import java.io.Serializable;

public abstract class ModelRequest<T> implements Serializable {
    protected String type;

    public abstract T asType(ModelRequestType type);

    public String getType() {
        return type;
    }


}
