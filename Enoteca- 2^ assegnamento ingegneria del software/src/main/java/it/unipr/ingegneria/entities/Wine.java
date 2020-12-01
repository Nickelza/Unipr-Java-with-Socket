package it.unipr.ingegneria.entities;


import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

/**
 * The {@code Wine} class has the method to manage the wine.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class Wine {

    private long _id;
    private String name;
    private Date year;
    private String producer;
    private String techNotes;
    private List<Vineyard> vineyards;

    private static final Logger logger = Logger.getLogger(Wine.class);

    /**
     * Default class constructor without Vineyards
     *
     * @param _id id of wine
     * @param name name of Wine
     * @param year year of Wine
     * @param producer producer of Wine
     * @param techNotes notes of Wine
     */
    public Wine(long _id, String name, Date year, String producer, String techNotes) {
        this._id = _id;
        this.name = name;
        this.year = year;
        this.producer = producer;
        this.techNotes = techNotes;
    }


    /**
     * Default class constructor without Vineyards
     *
     * @param _id id of wine
     * @param name name of Wine
     * @param year year of Wine
     * @param producer producer of Wine
     * @param techNotes notes of Wine
     * @param vineyards List of Vineyard of source
     */
    public Wine(long _id, String name, Date year, String producer, String techNotes, List<Vineyard> vineyards) {
        this._id = _id;
        this.name = name;
        this.year = year;
        this.producer = producer;
        this.techNotes = techNotes;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getTechNotes() {
        return techNotes;
    }

    public void setTechNotes(String techNotes) {
        this.techNotes = techNotes;
    }

    public List<Vineyard> getVineyards() {
        return vineyards;
    }

    public void setVineyards(List<Vineyard> vineyards) {
        this.vineyards = vineyards;
    }
}
