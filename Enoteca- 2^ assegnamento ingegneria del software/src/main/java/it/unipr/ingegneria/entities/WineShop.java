package it.unipr.ingegneria.entities;


import it.unipr.ingegneria.api.IObservable;
import it.unipr.ingegneria.api.IObserver;
import it.unipr.ingegneria.api.IStoreManager;
import it.unipr.ingegneria.api.IUserManager;
import it.unipr.ingegneria.exception.AvailabilityException;
import it.unipr.ingegneria.exception.RequiredValueException;
import it.unipr.ingegneria.entities.notifications.CustomerNotification;
import it.unipr.ingegneria.entities.user.Customer;
import it.unipr.ingegneria.entities.user.Employee;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.utils.Params;
import it.unipr.ingegneria.utils.Type;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The {@code WineShop} is a class for the manage the wine and people inside the shop.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @see it.unipr.ingegneria.api.IUserManager
 * @see it.unipr.ingegneria.api.IObservable
 * @see it.unipr.ingegneria.api.IObserver
 * @see it.unipr.ingegneria.api.IStoreManager
 */

public class WineShop implements
        IUserManager, IStoreManager<Wine>, IObservable<CustomerNotification>, IObserver {

    Logger logger = Logger.getLogger(WineShop.class);

    private Warehouse warehouse;
    private List<User> users;
    private ProvisioningManager provisioningManager;

    private List<CustomerNotification> observers = new ArrayList<>();

    public WineShop() {
        this.users = new ArrayList<>();

        this.warehouse = new Warehouse();
        this.provisioningManager = warehouse.getProvisioningManager();
        warehouse.addObserver(this);
    }

    /**
     * Method implementation to sell the wine at the client
     *
     * @param elements Map that contains info about Wine as name and quantity
     * @return list of wine
     * @throws AvailabilityException
     */
    @Override
    public List<Wine> sellWine(Map<Params, Object> elements) throws AvailabilityException {
        try {
            return this.warehouse.remove(elements);
        } catch (RequiredValueException e) {
            logger.info(e);
        } catch (AvailabilityException e) {
            throw new AvailabilityException();
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            this.warehouse.checkAvaibility();
        }
        return null;
    }

    /**
     * Method implementation for provision wine in warehouse
     *
     * @param elements Map that contains info about Wine as name and quantity
     */
    @Override
    public void provisionWine(Map<Params, Object> elements) {
        try {
            this.warehouse.add(elements);
        } catch (RequiredValueException e) {
            logger.equals(e.getMessage());
        } catch (Exception e) {
            logger.equals(e);
        }
    }

    /**
     * Method implementation to send the orders to clients
     */
    @Override
    public void sendOrders() {
        logger.info("Sending Orderes");
        users.stream()
                .filter(u -> u.getUserType().equals(Type.CLIENT))
                .map(user -> ((Customer) user))
                .map(customer -> customer.getOrders())
                .filter(orders -> !orders.isEmpty())
                .forEach((x) -> x.stream().forEach((i) -> i.setDelivered(true)));
    }

    /**
     * Research implementation for search wine by name
     *
     * @param name
     * @return list of wine
     */
    @Override
    public List<Wine> findByName(String name) {
        return this.warehouse.findByName(name);
    }

    /**
     * Research implementation for search wine by year
     *
     * @param d
     * @return list of wine
     */
    @Override
    public List<Wine> findByYear(int d) {
        return this.warehouse.findByYear(d);
    }

    /**
     * Implementation used for add user in the shop
     *
     * @param user
     */
    @Override
    public void addUser(User user) {
        if (user.getUserType().equals(Type.EMPLOYEE))
            this.provisioningManager.addObserver((Employee) user);
        this.users.add(user);
    }

    /**
     * Implementation used for remove user from the shop
     *
     * @param user User
     */
    @Override
    public void deleteUser(User user) {
        this.users.remove(user);
    }


    /**
     * Implementation for check if the shop contain and therefore has registed a certain user
     *
     * @param item
     * @return boolean
     */
    @Override
    public Boolean hasUser(User item) {
        return this.users.contains(item);
    }

    /**
     * Method called when the state of wine in the warehouse is changed
     *
     * @param o
     */
    @Override
    public void update(Object o) {
        List<Wine> winesAvailable = this.warehouse.getAvailableWines();

        for (CustomerNotification observer : this.observers) {
            String wineName = observer.getWineName();

            int sizes = winesAvailable.stream()
                    .filter(i -> i.getName().equals(wineName))
                    .collect(Collectors.toList()).size();

            Customer customer = observer.getCustomer();
            if (sizes >= observer.getQuantity())
                customer.update("");
        }
    }

    @Override
    public void addObserver(CustomerNotification user) {
        this.observers.add(user);
    }

    @Override
    public void removeObserver(CustomerNotification user) {
        this.observers.remove(user);
    }
}
