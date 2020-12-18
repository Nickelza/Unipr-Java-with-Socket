package it.unipr.ingegneria.utils;

import it.unipr.ingegneria.api.IObservable;
import it.unipr.ingegneria.entities.user.Employee;
import org.apache.log4j.Logger;


import java.io.Serializable;
import java.util.*;


/**
 * The {@code ProvisioningManger} is use to notify the Employees when when a certain wine is finished
 *  @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class ProvisioningManager implements IObservable<Employee>, Serializable {

    private List<Employee> managedEmployees;
    private Queue<Map> ordersQueue;
     private static final Logger logger = Logger.getLogger(ProvisioningManager.class);

    /**
     * Default constructor
     */
    public ProvisioningManager() {
        this.managedEmployees = new ArrayList<>();
        this.ordersQueue = new LinkedList<Map>();
    }


    /**
     * Add employee to the list of observer
     *
     * @param employee Employee
     */
    @Override
    public void addObserver(Employee employee) {
        managedEmployees.add(employee);
    }


    /**
     * Remove employee from the list of observer
     *
     * @param employee Employee
     */
    @Override
    public void removeObserver(Employee employee) {
        managedEmployees.remove(employee);
    }

    /**
     * Manage the provisioning of the Warehouse searching an available employee.
     * If not available add the element to a queue so to the next request when an employee that is not working
     * start to manage the request
     *
     * @param elements Map that contains info about Wine as name and quantity
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
