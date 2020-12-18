package it.unipr.ingegneria.models;

public class UserOrder {
    private String wineName;
    private Integer orderQty;

    public UserOrder(String wineName, Integer orderQty) {
        this.wineName = wineName;
        this.orderQty = orderQty;
    }

    public String getWineName() {
        return wineName;
    }

    public UserOrder setWineName(String wineName) {
        this.wineName = wineName;
        return this;
    }

    public Integer getOrderQty() {
        return orderQty;
    }

    public UserOrder setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
        return this;
    }
}
