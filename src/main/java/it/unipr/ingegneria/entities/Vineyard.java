package it.unipr.ingegneria.entities;

import it.unipr.ingegneria.entities.dao.DaoVineyard;
import it.unipr.ingegneria.entities.dao.DaoWineShop;

import java.sql.SQLException;

/**
 * The {@code Vineyard} defines the behaviour of a Vineyard
 */
public class Vineyard {

    /**
     * Unique Id for a Vineyard
     */
    private long id;

    /**
     * Vineyard's name
     *
     */
    private String name;

    /**
     * Data Access object for Database
     */
    private DaoVineyard daoVineyard = new DaoVineyard();

    /**
     * Default class constructor
     * @param name
     */
    public Vineyard(String name) throws SQLException {
        this.name = name;
        daoVineyard.add(this);
    }

    /**
     * Empty class constructor
     */
    public Vineyard() {
    }

    public String getName() {
        return name;
    }

    public Vineyard setName(String name) {
        this.name = name;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}