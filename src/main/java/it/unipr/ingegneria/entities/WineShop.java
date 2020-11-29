package it.unipr.ingegneria.entities;


import it.unipr.ingegneria.api.*;
import it.unipr.ingegneria.db.DTO.OrderDTO;
import it.unipr.ingegneria.db.persistance.*;
import it.unipr.ingegneria.db.persistance.relations.RelOrderUser;
import it.unipr.ingegneria.db.persistance.relations.RelOrderWine;
import it.unipr.ingegneria.db.persistance.relations.RelUserWineshop;
import it.unipr.ingegneria.db.persistance.relations.RelWineshopWarehouse;
import it.unipr.ingegneria.exception.AvailabilityException;
import it.unipr.ingegneria.exception.RequiredValueException;
import it.unipr.ingegneria.entities.notifications.CustomerNotification;
import it.unipr.ingegneria.entities.user.Customer;
import it.unipr.ingegneria.entities.user.Employee;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.utils.Params;
import it.unipr.ingegneria.utils.Type;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The {@code WineShop} is a class for the manage the wine and people inside the shop.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @see IUserManager
 * @see IObservable
 * @see IObserver
 * @see IStoreManager
 */

public class WineShop implements
        IUserManager, IStoreManager<Wine>, IObservable<CustomerNotification>, IObserver, Serializable, Persistable<WineShop> {

    private transient WineShopDAO wineShopDAO = WineShopDAO.getInstance();
    private transient UserDAO userDAO = UserDAO.getInstance();
    private transient OrderDAO orderDAO = OrderDAO.getInstance();
    private transient RelOrderUser relOrderUser = RelOrderUser.getInstance();
    private transient RelWineshopWarehouse relWineshopWarehouse = RelWineshopWarehouse.getInstance();
    private transient RelUserWineshop relUserWineshop = RelUserWineshop.getInstance();
    private transient RelOrderWine relOrderWine = RelOrderWine.getInstance();

    private transient Logger logger = Logger.getLogger(WineShop.class);

    private Integer id;
    private String name;

    private transient Warehouse warehouse;
    private transient List<User> users;
    private transient ProvisioningManager provisioningManager;

    private transient List<CustomerNotification> observers = new ArrayList<>();

    public WineShop(String name) {

        // Initialize self object and persist obtaining the id
        this.name = name;
        this.users = new ArrayList<>();
        this.warehouse = new Warehouse("WAREHOUSE-0");
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
    public Order sellWine(User to, Map<Params, Object> elements) throws AvailabilityException {
        try {
            List<Wine> wines = this.warehouse.remove(elements);
            Order order = new Order()
                    .setDate(new Date())
                    .setDelivered(false).setWine(wines).persist();
            relOrderUser.add(to, order);
            relOrderWine.addAll(wines, order);
            return order;

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
        } catch (Exception e) {
            logger.equals(e);
        }
    }


    /**
     * Implementation used for add user in the shop
     *
     * @param user
     */
    @Override
    public void addUser(User user) {
        if (user.getUserType().equals(Type.EMPLOYEE))
            provisioningManager.addObserver((Employee) user);

        userDAO.add(user);
        users.add(user);
        relUserWineshop.add(user, this);
    }

    /**
     * Method implementation to send the orders to clients
     */
    @Override
    public void sendOrders() {
        // logger.info("Sending Orderes");
        users.stream()
                .filter(u -> u.getUserType().equals(Type.CLIENT.toString()))
                .map(user -> ((Customer) user))
                .map(customer -> customer.getOrders())
                .filter(orders -> !orders.isEmpty())
                .forEach((x) -> x.stream().forEach((i) -> i.setDelivered(true)));
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
     * Method called when the state of wine in the warehouse is changed
     *
     * @param o Notification object with data
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    @Override
    public WineShop persist() {
        wineShopDAO.add(this);
        // Bind the WineShop object to own Warehouse
        relWineshopWarehouse.add(this, warehouse);
        return this;
    }

    @Override
    public User findByMailAndPassword(String email, String password) {
        return this.userDAO.executeLogin(email, password, this);
    }

    public List<User> getUsers() {
        return this.userDAO.findAll(this);
    }

    public List<OrderDTO> getOrders() {
        return this.orderDAO.findAll();
    }
}

