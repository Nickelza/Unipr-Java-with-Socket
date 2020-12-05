package it.unipr.ingegneria.request.create;

import it.unipr.ingegneria.request.ModelRequest;
import it.unipr.ingegneria.utils.ModelRequestType;

/**
 * The {@code CreateRequest} is a generic request containing the criteria Objects for creating.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
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