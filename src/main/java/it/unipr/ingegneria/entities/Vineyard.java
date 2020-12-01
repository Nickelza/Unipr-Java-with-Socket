package it.unipr.ingegneria.entities;

/**
 * The {@code Vineyard} defines the behaviour of a Vineyard
 */
public class Vineyard {
    /**
     * Vineyard's name
     *
     * @author Francesca Rossi, Everton Ejike, Ruslan Vasyunin
     */
    private String name;

    /**
     * Default class constructor
     * @param name
     */
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