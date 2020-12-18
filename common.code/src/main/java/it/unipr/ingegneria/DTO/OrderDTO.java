package it.unipr.ingegneria.DTO;

import java.io.Serializable;

/**
 * The {@code OrderDTO} represent a summary of a certain order
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class OrderDTO implements Serializable {
    private Integer userId;
    private Integer orderId;
    private String wineName;
    private String orderDate;
    private Boolean orderDelevered;
    private Integer orderQty;

    public Integer getUserId() {
        return userId;
    }

    public OrderDTO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public OrderDTO setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getWineName() {
        return wineName;
    }

    public OrderDTO setWineName(String wineName) {
        this.wineName = wineName;
        return this;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public OrderDTO setOrderDate(String orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public Boolean getOrderDelevered() {
        return orderDelevered;
    }

    public OrderDTO setOrderDelevered(Boolean orderDelevered) {
        this.orderDelevered = orderDelevered;
        return this;
    }

    public Integer getOrderQty() {
        return orderQty;
    }

    public OrderDTO setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
        return this;
    }
}
