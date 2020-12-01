package it.unipr.ingegneria.entities;

import java.util.Date;
import java.util.List;
/**
 * The {@code Order} class rappresent an order of wine
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class Order {

    private long ID;
    private Date date;
    private List<Wine> wine;
    private boolean delivered;

    public Order() {
    }

    /**
     * Main costructor
     * @param id order id
     * @param date Date
     * @param wine Wine
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
