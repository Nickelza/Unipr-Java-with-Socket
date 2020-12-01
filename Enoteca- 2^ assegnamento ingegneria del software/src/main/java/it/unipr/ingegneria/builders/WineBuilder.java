package it.unipr.ingegneria.builders;

import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.exception.AvailabilityException;

import java.util.Date;
import java.util.List;

/**
 * The @{@code WineBuilder } class is a alternative implementation of the build pattern for create the {@code Wine}
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class WineBuilder {
    private long id;
    private String name;
    private Date year;
    private String producer;
    private String techNotes;
    private List<Vineyard> vineyards;



    public long get_id() {
        return id;
    }

    public WineBuilder setId(long _id) {
        this.id = _id;
        return this;
    }

    public String getName() {
        return name;
    }

    public WineBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Date getYear() {
        return year;
    }

    public WineBuilder setYear(Date year) {
        this.year = year;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public WineBuilder setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public String getTechNotes() {
        return techNotes;
    }

    public WineBuilder setTechNotes(String techNotes) {
        this.techNotes = techNotes;
        return this;
    }

    public List<Vineyard> getVineyards() {
        return vineyards;
    }

    public WineBuilder setVineyards(List<Vineyard> vineyards) {
        this.vineyards = vineyards;
        return this;
    }

    /**
     * Method that after setted the intrested params build the object
     *
     * @return Wine
     */
    public Wine build() {
        Wine item = new Wine(id, name, year, producer, techNotes, vineyards);
        return item;
    }


}
