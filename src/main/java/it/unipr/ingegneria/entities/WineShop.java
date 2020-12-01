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
 * The {@code WineShop} class defines the behaviour of a Wine Shop
 * Implements {@code IUserManager}, {@code IStoreManager<T>}, {@code IObservable<T>}, {@code IObserver}
 *
 * @author Francesca Rossi, Everton Ejike, Ruslan Vasyunin
 * @see it.unipr.ingegneria.api.IUserManager
 * @see it.unipr.ingegneria.api.IStoreManager
 * @see it.unipr.ingegneria.api.IObservable
 * @see it.unipr.ingegneria.api.IObserver
 */
public class WineShop implements
        IUserManager, IStoreManager<Wine>, IObservable<CustomerNotification>, IObserver {

    /**
     * Log manager
     */
    Logger logger = Logger.getLogger(WineShop.class);

    /**
     * Warehouse //ToDo: Redifine
     */
    private Warehouse warehouse;
    /**
     * List of the wine shop users
     */
    private List<User> users;
    /**
     * Provisioning manager in charge of the wine shop
     */
    private ProvisioningManager provisioningManager;
    /**
     * Customer notifications to process
     */
    private List<CustomerNotification> observers = new ArrayList<>();

    /**
     * Default class constructor
     */
    public WineShop() {
        this.users = new ArrayList<>();
        this.provisioningManager = new ProvisioningManager();
        this.warehouse = new Warehouse();
        warehouse.addObserver(this);
    }


    /**
     * Method to sell a wine
     * @param elements
     * @return {@code List<Wine>}
     * @throws AvailabilityException
     */
    @Override
    public List<Wine> sellWine(Map<Params, Object> elements) throws AvailabilityException {
        List<Wine> workedWines = new ArrayList<>();
        try {
            workedWines = this.warehouse.remove(elements);
        } catch (RequiredValueException e) {
            logger.info(e);
        } catch (AvailabilityException e) {
            throw new AvailabilityException();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return workedWines;
    }


    /**
     * Method to provision wines to stock
     * @param elements
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

    @Override
    public void sendOrders() {
        users.stream()
                .filter(u -> u.getUserType().equals(Type.CLIENT))
                .map(user -> ((Customer) user))
                .map(customer -> customer.getOrders())
                .filter(orders -> !orders.isEmpty())
                .forEach((x) -> x.stream().forEach((i) -> i.setDelivered(true)));
    }

    @Override
    public List<Wine> findByName(String name) {
        return this.warehouse.findByName(name);
    }

    @Override
    public List<Wine> findByYear(int d) {
        return this.warehouse.findByYear(d);
    }


    @Override
    public void addUser(User user) {
        if (user.getUserType().equals(Type.EMPLOYEE))
            this.provisioningManager.addObserver((Employee) user);
        this.users.add(user);
    }

    @Override
    public void deleteUser(User user) {
        this.users.remove(user);
    }

    @Override
    public Boolean hasUser(User item) {
        return this.users.contains(item);
    }


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
        // ToDO: If you want generate random provisiong do it here
        Map<Params, Object> elements = new HashMap<>();
        elements.put(Params.NAME, user.getWineName());
        elements.put(Params.QTY, String.valueOf(user.getQuantity()));
        this.provisioningManager.handleProvisioning(elements);
    }

    @Override
    public void removeObserver(CustomerNotification user) {
        this.observers.remove(user);
    }
}
