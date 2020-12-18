package it.unipr.ingegneria.request.create;

import it.unipr.ingegneria.entities.user.User;

import java.io.Serializable;

/**
 * The {@code CreateOrderCriteria} class defines the criteria of request with params for create a Order .
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class CreateOrderCriteria implements Serializable {
    private User user;
    private String name;
    private Integer inQuantity;

    public User getUser() {
        return user;
    }

    public CreateOrderCriteria setUser(User user) {
        this.user = user;
        return this;
    }

    public String getName() {
        return name;
    }

    public CreateOrderCriteria setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getInQuantity() {
        return inQuantity;
    }

    public CreateOrderCriteria setInQuantity(Integer inQuantity) {
        this.inQuantity = inQuantity;
        return this;
    }
}
