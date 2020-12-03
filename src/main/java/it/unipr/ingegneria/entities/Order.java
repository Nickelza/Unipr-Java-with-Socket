package it.unipr.ingegneria.entities;

import it.unipr.ingegneria.api.Persistable;
import it.unipr.ingegneria.db.persistance.OrderDAO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The {@code Order} class represent an order of wine
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class Order implements Serializable, Persistable<Order> {
    private transient OrderDAO orderDAO = OrderDAO.getInstance();
    private int id;
    private Date date;
    private List<Wine> wine;
    private boolean delivered;


    /**
     * Default class for Order
     */
    public Order() {
    }

    public int getId() {
        return id;
    }

    public Order setId(int id) {
        this.id = id;
        return this;
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

    @Override
    public Order persist() {
        orderDAO.add(this);
        return this;
    }
}
