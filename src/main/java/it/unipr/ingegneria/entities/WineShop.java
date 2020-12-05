package it.unipr.ingegneria.entities;


import it.unipr.ingegneria.api.*;
import it.unipr.ingegneria.db.DTO.OrderDTO;
import it.unipr.ingegneria.db.persistance.OrderDAO;
import it.unipr.ingegneria.db.persistance.UserDAO;
import it.unipr.ingegneria.db.persistance.WineShopDAO;
import it.unipr.ingegneria.db.persistance.relations.RelOrderUser;
import it.unipr.ingegneria.db.persistance.relations.RelOrderWine;
import it.unipr.ingegneria.db.persistance.relations.RelUserWineshop;
import it.unipr.ingegneria.db.persistance.relations.RelWineshopWarehouse;
import it.unipr.ingegneria.entities.user.Employee;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.exception.AvailabilityException;
import it.unipr.ingegneria.exception.RequiredValueException;
import it.unipr.ingegneria.utils.Params;
import it.unipr.ingegneria.utils.Type;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The {@code WineShop} is a class for the manage the wine and people inside the shop.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @see IUserManager
 * @see IObservable
 * @see IObserver
 * @see IStoreManager
 */

public class WineShop implements IUserManager, IStoreManager<Wine>, IObserver, Serializable, Persistable<WineShop> {

    private transient WineShopDAO wineShopDAO = WineShopDAO.getInstance();
    private transient UserDAO userDAO = UserDAO.getInstance();
    private transient OrderDAO orderDAO = OrderDAO.getInstance();
    private transient RelOrderUser relOrderUser = RelOrderUser.getInstance();
    private transient RelWineshopWarehouse relWineshopWarehouse = RelWineshopWarehouse.getInstance();
    private transient RelUserWineshop relUserWineshop = RelUserWineshop.getInstance();
    private transient RelOrderWine relOrderWine = RelOrderWine.getInstance();
    private transient MulticastSocket multicastSocket;
    private transient InetAddress inetA;
    private transient Logger logger = Logger.getLogger(WineShop.class);

    private Integer id;
    private String name;

    private transient Warehouse warehouse;
    private transient List<User> users;
    private transient ProvisioningManager provisioningManager;


    public WineShop(String name, MulticastSocket multicastSocket, InetAddress inetA) {

        // Initialize
        this.name = name;
        this.users = new ArrayList<>();
        this.multicastSocket = multicastSocket;
        this.inetA = inetA;
        this.warehouse = new Warehouse("WAREHOUSE-0").persist();

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
                    .setDelivered(false)
                    .setWine(wines)
                    .persist();

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
        if (user.getUserType().equals(Type.EMPLOYEE.toString()))
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
        logger.info("Sending Orderes");
        orderDAO.updateOrders();
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
     * Execute the broadcast to client connected via socket the new available wines
     *
     * @param o Notification object with data
     */
    @Override
    public void update(Object o) {
        List<Wine> winesAvailable = this.warehouse.getAvailableWines();
        try {
            byte[] b = toByteArray(winesAvailable);
            DatagramPacket packet = new DatagramPacket(b, b.length, inetA, 4446);
            multicastSocket.send(packet);
        } catch (IOException e) {
            logger.info(e);
        }
    }


    /**
     * Persist the current entity and return itself with id generated by db
     *
     * @return WineShop instance with id
     */
    @Override
    public WineShop persist() {
        wineShopDAO.add(this);
        // Bind the WineShop object to own Warehouse
        relWineshopWarehouse.add(this, warehouse);
        return this;
    }

    /**
     * Find user in database with matched parameters
     *
     * @param email    email of user
     * @param password password of user
     * @return User instance with id
     */
    @Override
    public User findByMailAndPassword(String email, String password) {
        return this.userDAO.executeLogin(email, password, this);
    }

    /**
     * Find all Users
     *
     * @return List of User
     */
    public List<User> getUsers() {
        return this.userDAO.findAll(this);
    }

    /**
     * Find all Users by passed type
     *
     * @return List of filtered Users
     */
    public List<User> findByType(String type) {
        return this.userDAO.findByType(this, type);
    }

    /**
     * Find all executed orders
     *
     * @return List of OrderDTO
     */
    public List<OrderDTO> getOrders() {
        return this.orderDAO.findAll();
    }

    /**
     * Convert the passed object to byte array
     *
     * @param o Object to convert
     * @return byte array
     */
    private byte[] toByteArray(final Object o) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream s = new ObjectOutputStream(b);

        s.writeObject(o);
        s.flush();
        s.close();
        b.close();

        return b.toByteArray();
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
}

