package it.unipr.ingegneria.entities.notifications;

import it.unipr.ingegneria.entities.user.Customer;

/**
 * The  {@code CustomerNotification} class manages the notifications for when the wine is back in stock.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class CustomerNotification {

    private Customer customer;
    private String wineName;
    private int quantity;

    public Customer getCustomer() {
        return customer;
    }

    public int getQuantity() {
        return quantity;
    }

    public CustomerNotification setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public CustomerNotification setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public String getWineName() {
        return wineName;
    }

    public CustomerNotification setWineName(String wineName) {
        this.wineName = wineName;
        return this;
    }
}
