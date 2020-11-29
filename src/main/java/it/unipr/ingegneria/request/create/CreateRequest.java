package it.unipr.ingegneria.request;

import it.unipr.ingegneria.utils.ModelRequestType;

public class CreateRequest<MODEL> extends ModelRequest {
    private MODEL model;


    public CreateRequest() {
        super();
    }

    public CreateRequest withModel(MODEL model) {
        this.model = model;
        return this;
    }

    public MODEL getModel() {
        return this.model;
    }

    @Override
    public CreateRequest asType(ModelRequestType type) {
        this.type = type.toString();
        return this;
    }
}