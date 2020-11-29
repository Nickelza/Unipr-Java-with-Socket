package it.unipr.ingegneria.request.create;

import java.io.Serializable;

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
