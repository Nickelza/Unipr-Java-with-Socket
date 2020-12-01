package it.unipr.ingegneria.entities;

import it.unipr.ingegneria.api.IObservable;
import it.unipr.ingegneria.entities.user.Employee;
import it.unipr.ingegneria.utils.Params;
import org.apache.log4j.Logger;


import java.util.*;

/**
 * The {@code ProvisioningManager} defines the behaviour of the manager that takes care of provisioning
 * Implements {@code IObservable<T>} interface.
 *
 * @author Francesca Rossi, Everton Ejike, Ruslan Vasyunin
 * @see it.unipr.ingegneria.api.IObservable
 */
public class ProvisioningManager implements IObservable<Employee> {

    /**
     * List of Employees managed in the system by the Provisioning Manager
     */
    private List<Employee> managedEmployees;
    /**
     * Queue of orders to process
     */
    private Queue<Map> ordersQueue;
    /**
     * Log manager
     */
    private static final Logger logger = Logger.getLogger(ProvisioningManager.class);

    /**
     * Default class constructor
     */
    public ProvisioningManager() {
        this.managedEmployees = new ArrayList<>();
        this.ordersQueue = new LinkedList<Map>();
    }


    /**
     * Adds an employee to the list of employees to be observed
     * @param employee
     */
    @Override
    public void addObserver(Employee employee) {
        managedEmployees.add(employee);
    }

    /**
     * Removes an employee from the list of employees to be observed
     * @param employee
     */
    @Override
    public void removeObserver(Employee employee) {
        managedEmployees.remove(employee);
    }

    /**
     * Manages the request for provisioning
     * @param elements
     */
    public void handleProvisioning(Map<Params, Object> elements) {
        logger.info("Provisioning Manager received request");
        Optional<Employee> optionalEmployee = managedEmployees.stream()
                .filter(i -> !i.getWorking())
                .findFirst();
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setWorking(true);
            if (!ordersQueue.isEmpty())
                ordersQueue.stream().forEach(order -> employee.update(elements));
            employee.update(elements);
        }
        if (!optionalEmployee.isPresent()) {
            this.ordersQueue.add(elements);
        }
    }

}
