package it.unipr.ingegneria.entities;


import it.unipr.ingegneria.api.Persistable;
import it.unipr.ingegneria.db.persistance.WineDAO;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Wine} class has the method to manage the wine.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class Wine implements Serializable, Persistable<Wine> {

    private int id;
    private String name;
    private int year;
    private String producer;
    private String techNotes;
    private List<Vineyard> vineyards;
    private transient WineDAO wineRepo = WineDAO.getInstance();

    private static final Logger LOGGER = Logger.getLogger(Wine.class);


    public Wine() {
    }

    /**
     * Default class constructor without Vineyards
     *
     * @param name      name of Wine
     * @param year      year of Wine
     * @param producer  producer of Wine
     * @param techNotes notes of Wine
     */
    public Wine(String name, int year, String producer, String techNotes) {
        this.name = name;
        this.year = year;
        this.producer = producer;
        this.techNotes = techNotes;
    }


    /**
     * Default class constructor with Vineyards
     *
     * @param name      name of Wine
     * @param year      year of Wine
     * @param producer  producer of Wine
     * @param techNotes notes of Wine
     * @param vineyards List of Vineyard of source
     */
    public Wine(String name, int year, String producer, String techNotes, List<Vineyard> vineyards) {

        this.name = name;
        this.year = year;
        this.producer = producer;
        this.techNotes = techNotes;
        this.vineyards = vineyards;
    }

    /**
     * Constructor with Vineyards and id
     * Used when build the object itself from ResultSet
     *
     * @param id        name of Wine
     * @param name      name of Wine
     * @param year      year of Wine
     * @param producer  producer of Wine
     * @param techNotes notes of Wine
     * @param vineyards List of Vineyard of source
     */
    public Wine(int id, String name, int year, String producer, String techNotes, List<Vineyard> vineyards) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.producer = producer;
        this.techNotes = techNotes;
        this.vineyards = vineyards;
    }

    public int getId() {
        return id;
    }

    public Wine setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Wine setName(String name) {
        this.name = name;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Wine setYear(int year) {
        this.year = year;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public Wine setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public String getTechNotes() {
        return techNotes;
    }

    public Wine setTechNotes(String techNotes) {
        this.techNotes = techNotes;
        return this;
    }

    public List<Vineyard> getVineyards() {
        return vineyards;
    }

    public Wine setVineyards(List<Vineyard> vineyards) {
        this.vineyards = vineyards;
        return this;
    }

    public WineDAO getWineRepo() {
        return wineRepo;
    }

    public Wine setWineRepo(WineDAO wineRepo) {
        this.wineRepo = wineRepo;
        return this;
    }

    /**
     * Override of equals method, compare the current instance with passed object
     * used when call in contains method in List
     *
     * @param o Object to compare with the instance
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Wine)) {
            return false;
        }
        // typecast o to Complex so that we can compare data members
        Wine c = (Wine) o;

        return this.id == c.getId();
    }

    /**
     * Build the object from a ResultSet
     *
     * @param rs Result Set
     */
    public static Wine valueOf(ResultSet rs) throws SQLException {
        Integer WINE_ID = rs.getInt("WINE_ID");
        String WINE_NAME = rs.getString("WINE_NAME");
        Integer WINE_YEAR = rs.getInt("WINE_YEAR");
        String WINE_PRODUCER = rs.getString("WINE_PRODUCER");
        String WINE_TECHNOTES = rs.getString("WINE_TECHNOTES");

        Integer VINEYARD_ID = rs.getInt("VINEYARD_ID");
        Vineyard v;
        List<Vineyard> VINEYEARDS = new ArrayList<>();

        if (VINEYARD_ID != null) {
            String VINEYARD_NAME = rs.getString("VINEYARD_NAME");
            v = new Vineyard().setId(VINEYARD_ID).setName(VINEYARD_NAME);
            VINEYEARDS.add(v);
        }

        return
                new Wine()
                        .setId(WINE_ID)
                        .setName(WINE_NAME)
                        .setProducer(WINE_PRODUCER)
                        .setYear(WINE_YEAR)
                        .setTechNotes(WINE_TECHNOTES)
                        .setVineyards(VINEYEARDS);
    }

    @Override
    public Wine persist() {
        wineRepo.add(this);
        LOGGER.info("Inserted Wine with ID: " + this.id + " in WINE Table");
        return this;
    }
}
