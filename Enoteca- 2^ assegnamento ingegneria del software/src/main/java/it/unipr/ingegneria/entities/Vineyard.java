package it.unipr.ingegneria.entities;

/**
 * The {@code Vineyard} class contains the info of the vineyard
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class Vineyard {
    private String name;

    public Vineyard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Vineyard setName(String name) {
        this.name = name;
        return this;
    }
}