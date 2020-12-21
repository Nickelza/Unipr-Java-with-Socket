package it.unipr.ingegneria.controllers;

import it.unipr.ingegneria.controllers.users.CustomerController;
import it.unipr.ingegneria.controllers.users.EmployeeController;
import it.unipr.ingegneria.models.UserOrder;
import it.unipr.ingegneria.models.utils.TypeNotify;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The {@code NotifyWineController} is a class that manage the popup about the notify of wine
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class NotifyWineController {
    private static List<UserOrder> waitingForAvaibility=new ArrayList<UserOrder>();
    private static Map<String, Long> wineAvaible;
    private  String message;
    private boolean notify;

    public NotifyWineController() { }

    public NotifyWineController setWaitingForAvaibility(UserOrder order) {
        this.waitingForAvaibility.add(order);
        return this;
    }

    public List<UserOrder> getWaitingForAvaibility() {
        return waitingForAvaibility;
    }

    public NotifyWineController setWineAvaible(Map<String, Long> order) {
        NotifyWineController.wineAvaible = order;
        return this;
    }
    private void setUp()
    {
        this.message="";
        this.notify=false;
    }
    public void requestOfWine()
    {
        try {
            this.setUp();
            for (Map.Entry<String, Long> entry : wineAvaible.entrySet()) {
                for (UserOrder order : waitingForAvaibility) {
                    System.out.println(order.getWineName() + "-" + order.getOrderQty());
                    if (entry.getKey().equals(order.getWineName())) {
                        if (entry.getValue() >= order.getOrderQty()) {
                            this.message=this.message+ "The wine " + order.getWineName() + " is already aviable whit " + entry.getValue() + " bottles \n";
                            this.notify=true;
                            waitingForAvaibility.remove(order);
                        }
                    }
                }
            }
            this.showNotify(TypeNotify.REQUEST_WINE);
        }
        catch (Exception e)
        {
            //null
        }
    }
    public void requestOfProvisioning()
    {
        this.setUp();
        try {
            if(wineAvaible==null)
            {
                for (UserOrder order : waitingForAvaibility) {
                        this.message += "A client request " + order.getOrderQty() + " bottle of wine " + order.getWineName() + "\n";
                        this.notify=true;
                    }
            }
            else {
                for (Map.Entry<String, Long> entry : wineAvaible.entrySet()) {
                    for (UserOrder order : waitingForAvaibility) {
                        if ((!entry.getKey().equals(order.getWineName()))||(entry.getValue() < order.getOrderQty())) {
                            this.message += "A client request " + order.getOrderQty() + " bottle of wine " + order.getWineName() + "\n";
                            this.notify=true;
                        }
                    }
                }
            }
            this.showNotify(TypeNotify.REQUEST_PROVISIONING);
        }
        catch (Exception e)
        {
            //null
        }

    }
    private void showNotify(TypeNotify type)
    {
        if (notify) {
            if (type == TypeNotify.REQUEST_PROVISIONING) {
                new EmployeeController().manageNotify(this.message);
            } else {
                new CustomerController().manageNotify(this.message);
            }
        }
    }
}

