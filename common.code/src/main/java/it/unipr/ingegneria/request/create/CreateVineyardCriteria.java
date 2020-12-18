package it.unipr.ingegneria.request.create;

import java.io.Serializable;

/**
 * The {@code CreateVineyardCriteria} class defines the criteria of request with params for create Vineyard .
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class CreateVineyardCriteria implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public CreateVineyardCriteria setName(String name) {
        this.name = name;
        return this;
    }
}
