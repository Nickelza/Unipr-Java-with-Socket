package it.unipr.ingegneria.response;


import java.io.Serializable;

/**
 * The {@code ModelResponse} class represent the response with a generic single object as result .
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class ModelResponse<T> implements Serializable {
    private T model;

    private void setModel(T model) {
        this.model = model;
    }

    public ModelResponse<T> withModel(T model) {
        this.model = model;
        return this;
    }

    public T getModel() {
        return model;
    }
}
