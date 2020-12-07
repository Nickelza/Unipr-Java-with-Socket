package it.unipr.ingegneria.entities;


import it.unipr.ingegneria.api.IObservable;
import it.unipr.ingegneria.api.IObserver;
import it.unipr.ingegneria.api.IStoreManager;
import it.unipr.ingegneria.api.IUserManager;
import it.unipr.ingegneria.entities.dao.DaoWineShop;
import it.unipr.ingegneria.exception.AvailabilityException;
import it.unipr.ingegneria.exception.RequiredValueException;
import it.unipr.ingegneria.entities.notifications.CustomerNotification;
import it.unipr.ingegneria.entities.user.Customer;
import it.unipr.ingegneria.entities.user.Employee;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.utils.Params;
import it.unipr.ingegneria.utils.Type;
import org.apache.log4j.Logger;

import java.sql.SQLException;
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
     * Unique Id for a WineShop
     */
    private long id;

    /**
     * Name of a WineShop
     */
    private String name;

    /**
     * Log manager
     */
    Logger logger = Logger.getLogger(WineShop.class);

    /**
     * Warehouse //ToDo: Redefine
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
     * Data Access object for Database
     */
    private DaoWineShop daoWineShop = new DaoWineShop();

    /**
     * Default class constructor
     */
    public WineShop(String _name) throws SQLException {
        this.name = _name;
        this.users = new ArrayList<>();
        this.provisioningManager = new ProvisioningManager();
        this.warehouse = new Warehouse("wh1");
        warehouse.addObserver(this);
        daoWineShop.add(this);
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

    public long getId() {
        return id;
    }

    public void setId(long _id) {
        this.id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
