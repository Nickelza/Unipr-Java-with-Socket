package it.unipr.ingegneria.entities;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The {@code Vineyard} class contains the info of the vineyard
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class Vineyard implements Serializable {

    private int id;
    private String name;


    /**
     * Default class constructor
     */
    public Vineyard() {
    }

    public int getId() {
        return id;
    }

    public Vineyard setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Vineyard setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Build the object from a ResultSet
     *
     * @param rs Result Set
     */
    public static Vineyard valueOf(ResultSet rs) throws SQLException {
        Integer VINEYARD_ID = rs.getInt("ID");
        String VINEYARD_NAME = rs.getString("NAME");
        return new Vineyard().setId(VINEYARD_ID).setName(VINEYARD_NAME);
    }
}