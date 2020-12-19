package it.unipr.ingegneria.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WineAvailability {
    private static List<UserOrder> waitingForAvaibility=new ArrayList<UserOrder>();
    private static Map<String, Long> wineAvaible;

    public WineAvailability() { }

    public WineAvailability setWaitingForAvaibility(UserOrder order) {
        this.waitingForAvaibility.add(order);
        return this;
    }

    public List<UserOrder> getWaitingForAvaibility() {
        return waitingForAvaibility;
    }

    public WineAvailability  setWineAvaible(Map<String, Long> order) {
        WineAvailability.wineAvaible = order;
        return this;
    }
    public void check()
    {
        for (Map.Entry<String, Long> entry : wineAvaible.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
            for(UserOrder order:waitingForAvaibility){
                if((entry.getKey()==order.getWineName())&&(entry.getValue()>=order.getOrderQty())){
                    System.out.println("Wine is already available");
                }

            }
        }
    }
}
