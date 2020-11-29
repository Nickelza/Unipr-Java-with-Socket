package it.unipr.ingegneria.response;

import java.io.Serializable;
import java.util.List;

public class ModelListResponse implements Serializable {
    private List models;

    private void setModels(List models) {
        this.models = models;
    }

    public ModelListResponse withModels(List models) {
        this.models = models;
        return this;
    }

    public List getModels() {
        return models;
    }
}
