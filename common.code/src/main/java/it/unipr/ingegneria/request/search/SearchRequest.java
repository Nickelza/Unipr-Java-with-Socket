package it.unipr.ingegneria.request.search;


import it.unipr.ingegneria.request.ModelRequest;
import it.unipr.ingegneria.utils.ModelRequestType;

/**
 * The {@code SearchRequest} is a generic request containing the criteria Objects for search .
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class SearchRequest<MODEL> extends ModelRequest {
    private MODEL model;


    public SearchRequest() {
        super();
    }

    public SearchRequest withModel(MODEL model) {
        this.model = model;
        return this;
    }

    public MODEL getModel() {
        return this.model;
    }

    @Override
    public SearchRequest asType(ModelRequestType type) {
        this.type = type.toString();
        return this;
    }
}
