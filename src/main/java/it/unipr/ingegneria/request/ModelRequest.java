package it.unipr.ingegneria.request;

import it.unipr.ingegneria.utils.ModelRequestType;

import java.io.Serializable;

public abstract class ModelRequest<T> implements Serializable {
    protected String type;

    public abstract T asType(ModelRequestType type);

    public String getType() {
        return type;
    }


}
