package it.unipr.ingegneria.entities.user;

import it.unipr.ingegneria.api.IObserver;
import it.unipr.ingegneria.utils.LogMessages;
import it.unipr.ingegneria.utils.Params;
import org.apache.log4j.Logger;

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
    private transient static final Logger LOGGER = Logger.getLogger(Employee.class);


    /**
     * Constructor used when instantiate the object without knowing any data. Will be enriched later
     */
    public Employee() {
        super();
    }


    /**
     * Method to restock / execute provision  {@code Wine} in {@code Warehouse}
     *
     * @param elements Map with params info
     */
    public void provisionWine(Map<Params, Object> elements) {
        LOGGER.info(LogMessages.employeeProvisiongWine(this, ((String) elements.get(Params.NAME))));
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


