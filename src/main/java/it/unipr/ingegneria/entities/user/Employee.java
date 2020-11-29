package it.unipr.ingegneria.entities.user;

import it.unipr.ingegneria.api.IObserver;
import it.unipr.ingegneria.api.IStoreManager;
import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.entities.WineShop;
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
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @see User
 * @see IObserver
 */
public class Employee extends User implements IObserver {

    private Boolean isWorking;


    /**
     * Constructor used when instantiate the object without knowing any data. Will be enriched later
     */
    public Employee() {
        super();
    }

    /**
     * Constructor used when instantiate the object without knowing the id, obtained after it persisted
     *
     * @param name     Admin name
     * @param surname  Admin surname
     * @param email    Admin Email
     * @param password Admin Password
     * @param wineShop Wine Shop Object
     */
    public Employee(String name, String surname, String email, String password, WineShop wineShop) {
        super(name, surname, email, password, Type.EMPLOYEE, wineShop);
    }

    /**
     * Constructor used when instantiate the object  knowing the id, obtained when read the Object from a ResultSet
     *
     * @param id       Employee id
     * @param name     Employee name
     * @param surname  Employee surname
     * @param email    Employee Email
     * @param password Employee Password
     */
    public Employee(int id, String name, String surname, String email, String password) {
        super(id, name, surname, email, password, Type.ADMIN);
    }

    /**
     * Method to restock / execute provision  {@code Wine} in {@code Warehouse}
     *
     * @param elements Map with params info
     */
    public void provisionWine(Map<Params, Object> elements) {

        this.wineshop.provisionWine(elements);
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
        this.wineshop.sendOrders();
    }

    /**
     * Receive notification from {@code ProvisioningManager} and start provisioning the Warehouse
     *
     * @param o state of notifcation
     */
    @Override
    public void update(Object o) {
        Map<Params, Object> elements = (Map<Params, Object>) o;
        provisionWine(elements);
    }
}


