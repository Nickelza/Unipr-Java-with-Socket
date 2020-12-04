package it.unipr.ingegneria.entities.user;

import it.unipr.ingegneria.entities.Order;

import java.util.List;


/**
 * The {@code Customer} class defines the behavior of the User type Customer.
 * Extends the class {@code User}
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @see User
 */
public class Customer extends User {

    private List<Order> orders;

    /**
     * Constructor used when instantiate the object without knowing any data. Will be enriched later
     */

    public Customer() {
        super();
    }

    /**
     * Method to get all orders of current customer
     *
     * @return list of {@code Order}
     */
    public List<Order> getOrders() {
        return orders;
    }

}
