package it.unipr.ingegneria.response;


import java.io.Serializable;

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
