package it.unipr.ingegneria.entities;


import it.unipr.ingegneria.entities.dao.DaoWine;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * The class {@code Wine} defines the behaviour of a Wine
 *
 * @author Francesca Rossi, Everton Ejike, Ruslan Vasyunin
 * @see it.unipr.ingegneria.builders.WineBuilder //ToDo: Relation between them
 */
public class Wine {

    /**
     * Unique ID for a wine
     */
    private long _id;
    /**
     * Wine's name
     */
    private String name;
    /**
     * Wine's year of production
     */
    private Date year;
    /**
     * Wine's producer
     */
    private String producer;
    /**
     * Technical notes about the Wine
     */
    private String techNotes;
    /**
     * List of Vineyards which produced grapes for the Wine
     */
    private List<Vineyard> vineyards;

    /**
     * Log manager
     */
    private static final Logger logger = Logger.getLogger(Wine.class);

    /**
     * Data Access object for Database
     */
    private DaoWine daoWine = new DaoWine();

    public Wine() {
    }

    /**
     * Default class constructor
     * @param _id
     * @param name
     * @param year
     * @param producer
     * @param techNotes
     */
    public Wine(long _id, String name, Date year, String producer, String techNotes) throws SQLException {
        this._id = _id;
        this.name = name;
        this.year = year;
        this.producer = producer;
        this.techNotes = techNotes;
        daoWine.add(this);
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

    /**
     * Indicates if the given item is a Wine or not
     * @param anObject
     * @return {@code Boolean}
     */
    @Override
    public boolean equals(Object anObject) {
        if (!(anObject instanceof Wine)) {
            return false;
        }
        Wine otherMember = (Wine) anObject;
        return otherMember.get_id() == this._id;
    }
}
