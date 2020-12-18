package it.unipr.ingegneria.entities;

import it.unipr.ingegneria.utils.ProvisioningManager;

import java.io.Serializable;

public abstract class AbstractWarehouse implements Serializable {
    private Integer id;
    private String name;
    protected ProvisioningManager provisioningManager;

    public AbstractWarehouse(String name) {
        this.name = name;
        this.provisioningManager = new ProvisioningManager();
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return a Provisioning Manager assigned to current Warehouse
     *
     * @return ProvisioningManager
     */
    public ProvisioningManager getProvisioningManager() {
        return provisioningManager;
    }

    public AbstractWarehouse setProvisioningManager(ProvisioningManager provisioningManager) {
        this.provisioningManager = provisioningManager;
        return this;
    }

    public abstract void checkAvailability();

}
