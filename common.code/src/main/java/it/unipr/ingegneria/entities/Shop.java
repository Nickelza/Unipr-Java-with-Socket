package it.unipr.ingegneria.entities;

import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.utils.ProvisioningManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Shop implements Serializable {

    private Integer id;
    private String name;

    protected transient AbstractWarehouse warehouse;
    protected transient List<User> users;
    protected transient ProvisioningManager provisioningManager;


    public Shop(String name, AbstractWarehouse warehouse) {
        this.name = name;
        this.warehouse = warehouse;
        this.users = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public Shop setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }

    public Shop setUsers(List<User> users) {
        this.users = users;
        return this;
    }

    public Shop setName(String name) {
        this.name = name;
        return this;
    }

    public AbstractWarehouse getWarehouse() {
        return warehouse;
    }

    public Shop setWarehouse(AbstractWarehouse warehouse) {
        this.warehouse = warehouse;
        return this;
    }
}
