package it.unipr.ingegneria.entities;

import java.util.Date;
import java.util.List;

/**
 * The {@code Order} defines the behaviour of the Order object
 *
 * @author Francesca Rossi, Everton Ejike, Ruslan Vasyunin
 */
public class Order {

    /**
     * Order unique ID
     */
    private long ID;
    /**
     * Order date
     */
    private Date date;
    /**
     * List of Wines that were ordered
     */
    private List<Wine> wine;
    /**
     * If the order has been delivered or not
     */
    private boolean delivered;

    public Order() {
    }

    /**
     * Default class constructor
     * @param id
     * @param date
     * @param wine
     */
    public Order(long id, Date date, List<Wine> wine) {
        this.ID = id;
        this.date = date;
        this.wine = wine;
        this.delivered = false;
    }

    public Order setID(long ID) {
        this.ID = ID;
        return this;
    }

    public long getID() {
        return ID;
    }

    public Order setDate(Date date) {
        this.date = date;
        return this;
    }

    public Date getDate() {
        return date;
    }


    public Order setWine(List<Wine> wine) {
        this.wine = wine;
        return this;
    }

    public List<Wine> getWine() {
        return wine;
    }

    public Order setDelivered(boolean delivered) {
        this.delivered = delivered;
        return this;
    }

    public boolean isDelivered() {
        return delivered;
    }
}
