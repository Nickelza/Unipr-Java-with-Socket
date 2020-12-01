package it.unipr.ingegneria.entities.user;

import it.unipr.ingegneria.api.IObserver;
import it.unipr.ingegneria.api.IStoreManager;
import it.unipr.ingegneria.utils.LogMessages;
import it.unipr.ingegneria.utils.Params;
import it.unipr.ingegneria.utils.Type;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code Employee} class defines the behavior of the User type Customer.
 * Extends the class {@code User} and implements {@code IObserver} interface
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @see it.unipr.ingegneria.entities.user.User
 * @see it.unipr.ingegneria.api.IObserver
 */
public class Employee extends User implements IObserver {

    private IStoreManager wineShop;
    private Boolean isWorking;
    private static final Logger logger = Logger.getLogger(Employee.class);
    /**
     * Default class constructor
     * @param id Id
     * @param name Name
     * @param surname Surname
     * @param email Email
     * @param password Password
     * @param wineShop IStoreManager for handling Warehouse
     */
    public Employee(long id, String name, String surname, String email, String password, IStoreManager wineShop) {
        super(id, name, surname, email, password, Type.EMPLOYEE);
        this.wineShop = wineShop;

    }
    /**
     * Method to restock / execute provision  {@code Wine} in {@code Warehouse}
     * @param name name
     * @param quantity quantity
     */
    public void provisionWine(String name, int quantity) {
        logger.info(LogMessages.employeeProvisiongWine(this, name));
        Map<Params, Object> elements = new HashMap<>();
        elements.put(Params.NAME, name);
        elements.put(Params.QTY, String.valueOf(quantity));
        elements.put(Params.DATE, new Date());
        this.wineShop.provisionWine(elements);
        this.isWorking = false;
    }

    public Boolean getWorking() {
        return isWorking;
    }


    public Employee setWorking(Boolean working) {
        isWorking = working;
        return this;
    }

    /**
     * Method to send ordered wines to customers
     */
    public void sendOrders() {
        wineShop.sendOrders();
    }

    /**
     * Receive notification from {@code ProvisioningManager} and start provisioning the Warehouse
     * @param o state of notifcation
     */
    @Override
    public void update(Object o) {
        Map<Params, Object> elements = (Map<Params, Object>) o;
        String name = (String) elements.get(Params.NAME);
        Integer number;
        try {
            number = Integer.parseInt((String) elements.get(Params.QTY));
        } catch (Exception e) {
            number = (Integer) elements.get(Params.QTY);
        }

        provisionWine(name, number);
    }
}


