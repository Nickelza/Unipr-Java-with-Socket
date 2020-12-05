package it.unipr.ingegneria.response;

import java.io.Serializable;
import java.util.List;

/**
 * The {@code ModelListResponse} class represent the response with a generic list as result .
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
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
