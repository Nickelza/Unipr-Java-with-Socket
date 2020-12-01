package it.unipr.ingegneria.entities.user;

import it.unipr.ingegneria.entities.Order;
import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.api.IAuthentication;
import it.unipr.ingegneria.api.IObserver;
import it.unipr.ingegneria.exception.AvailabilityException;
import it.unipr.ingegneria.exception.NotFoundException;
import it.unipr.ingegneria.entities.notifications.CustomerNotification;
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
 * @see it.unipr.ingegneria.api.IAuthentication
 * @see it.unipr.ingegneria.api.IObserver
 * @see it.unipr.ingegneria.entities.user.User
 */
public class Customer extends User implements IAuthentication, IObserver {


    private static final Logger logger = Logger.getLogger(Customer.class);
    private CustomerNotification notification;
    private Boolean isAuthenticated;
    private List<Order> orders;

    /**
     * Default class constructor
     *
     * @param id Customer Id
     * @param name Customer Name
     * @param surname Customer Surname
     * @param email Customer email
     * @param password Customer Password
     * @param wineShop Desired Wineshop
     */
    public Customer(long id, String name, String surname, String email, String password, WineShop wineShop) {
        super(id, name, surname, email, password, Type.CLIENT);
        this.orders = new ArrayList<>();
        this.isAuthenticated = false;
        setWineshop(wineShop);
    }

    /**
     * Method to create an order
     *
     * @param name Name of wine
     * @param quantity Desired quantity
     */
    public void order(String name, int quantity) {

        if (isAuthenticated) {
            try {

                Map<Params, Object> elements = new HashMap<>();
                elements.put(Params.QTY, String.valueOf(quantity));
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
        } else {
            logger.info(LogMessages.userNoAuth(this));
        }
    }


    /**
     * Method to login to the system
     *
     * @param email Email
     * @param password Password
     * @throws Exception
     */
    @Override
    public void login(String email, String password) throws Exception {
        if (!this.getWineshop().hasUser(this))
            throw new NotFoundException();

        isAuthenticated = this.getEmail().equals(email) && this.getPassword().equals(password) && this.getWineshop().hasUser(this);
    }

    /**
     * Method to log out of the system
     *
     * @throws Exception
     */
    @Override
    public void logout() throws Exception {
        if (!this.getWineshop().hasUser(this))
            throw new NotFoundException();

        this.getWineshop().deleteUser(this);
        isAuthenticated = this.getWineshop().hasUser(this);
    }

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
     * Method to find a list of wines by name
     *
     * @param name of searched Wine
     * @return list of {@code Wine}
     */
    public List<Wine> findByName(String name) {
        return this.getWineshop().findByName(name);
    }


    /**
     * Method to find a list of wines by year
     *
     * @param d Year of searched Wine
     * @return list of {@code Wine}
     */
    public List<Wine> findByYear(int d) {
        return this.getWineshop().findByYear(d);
    }

    /**
     * Method to get all orders of current customer
     * @return list of {@code Order}
     */
    public List<Order> getOrders() {
        return orders;
    }
}
