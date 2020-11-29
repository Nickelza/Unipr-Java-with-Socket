package it.unipr.ingegneria.request.create;

import java.io.Serializable;

public class VineyardCriteria implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public VineyardCriteria setName(String name) {
        this.name = name;
        return this;
    }
}
