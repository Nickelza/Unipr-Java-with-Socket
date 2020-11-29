package it.unipr.ingegneria.entities.user;

import it.unipr.ingegneria.entities.Order;
import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.api.IAuthentication;
import it.unipr.ingegneria.api.IObserver;
import it.unipr.ingegneria.exception.AvailabilityException;
import it.unipr.ingegneria.entities.notifications.CustomerNotification;
import it.unipr.ingegneria.db.persistance.UserDAO;
import it.unipr.ingegneria.utils.LogMessages;
import it.unipr.ingegneria.utils.Params;
import it.unipr.ingegneria.utils.Type;
import org.apache.log4j.Logger;

import java.util.*;


/**
 * The {@code Customer} class defines the behavior of the User type Customer.
 * Extends the class {@code User} and implements {@code IAuthentication} and {@code IObserver} interface
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @see IAuthentication
 * @see IObserver
 * @see User
 */
public class Customer extends User implements IObserver {


    private transient static final Logger logger = Logger.getLogger(Customer.class);
    private transient UserDAO userDAO = UserDAO.getInstance();
    private transient CustomerNotification notification;
    private List<Order> orders;

    /**
     * Constructor used when instantiate the object without knowing any data. Will be enriched later
     */

    public Customer() {
        super();
    }

    /**
     * Constructor used when instantiate the object without knowing the id, obtained after it persisted
     *
     * @param name     Customer name
     * @param surname  Customer surname
     * @param email    Customer Email
     * @param password Customer Password
     * @param wineShop Wine Shop Object
     */
    public Customer(String name, String surname, String email, String password, WineShop wineShop) {
        super(name, surname, email, password, Type.CLIENT, wineShop);
        this.orders = new ArrayList<>();

    }

    /**
     * Method to create an order
     *
     * @param name     Name of wine
     * @param quantity Desired quantity

    public void order(String name, int quantity) {
    try {
    Map<Params, Object> elements = new HashMap<>();
    elements.put(Params.QTY, quantity);
    elements.put(Params.NAME, name);
    logger.info("User " + this.getName() + " " + this.getSurname() + " asked for " + name);
    List<Wine> workedWines = this.getWineshop().sellWine(elements);

    Order order = new Order()
    .setID(Long.valueOf(orders.size() + 1))
    .setDate(new Date())
    .setWine(workedWines)
    .setDelivered(false);

    this.orders.add(order);

    logger.info(LogMessages.userCompletedOrder(this));
    } catch (AvailabilityException e) {
    logger.info(LogMessages.wineEnded(this, name));
    notification =
    new CustomerNotification()
    .setCustomer(this)
    .setQuantity(quantity)
    .setWineName(name);
    this.getWineshop().addObserver(notification);
    } catch (Exception e) {
    logger.info(e);
    }
    }
     */

    /**
     * Receive notification from Warehouse and print to console the message with info of available Wine
     *
     * @param o Object that can contains info of notification
     */
    @Override
    public void update(Object o) {
        logger.info(LogMessages.userNotification(this, notification));
        this.getWineshop().removeObserver(notification);

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
