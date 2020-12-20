package it.unipr.ingegneria.controllers;

import it.unipr.ingegneria.controllers.users.ClientController;
import it.unipr.ingegneria.controllers.users.EmployeeController;
import it.unipr.ingegneria.models.UserOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotifyWineController {
    private static List<UserOrder> waitingForAvaibility=new ArrayList<UserOrder>();
    private static Map<String, Long> wineAvaible;

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
    public void check()
    {
        try {
            String message="";
            for (Map.Entry<String, Long> entry : wineAvaible.entrySet()) {
                System.out.println(entry.getKey() + "/" + entry.getValue());
                for (UserOrder order : waitingForAvaibility) {
                    System.out.println(order.getWineName() + "-" + order.getOrderQty());
                    if (entry.getKey().equals(order.getWineName())) {
                        if (entry.getValue() >= order.getOrderQty()) {
                            message=message+ "The wine " + order.getWineName() + " is already aviable whit " + entry.getValue() + " bottles \n";
                            new ClientController().manageNotify(message);
                            waitingForAvaibility.remove(order);
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            //null
        }
    }
    public void requestOfWine()
    {
        String message="";
        try {
            if(wineAvaible.isEmpty())
            {
                for (UserOrder order : waitingForAvaibility) {
                        message = "A client request " + order.getOrderQty() + " bottle of wine " + order.getWineName() + "\n";
                        new EmployeeController().manageNotify(message);
                    }

            }
            else {
                for (Map.Entry<String, Long> entry : wineAvaible.entrySet()) {
                    for (UserOrder order : waitingForAvaibility) {
                        if ((!entry.getKey().equals(order.getWineName()))||(entry.getValue() < order.getOrderQty())) {
                            message = "A client request " + order.getOrderQty() + " bottle of wine " + order.getWineName() + "\n";
                            new EmployeeController().manageNotify(message);
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            //null
        }
    }
}
