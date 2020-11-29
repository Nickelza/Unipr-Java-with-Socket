package it.unipr.ingegneria.request.create;

import it.unipr.ingegneria.entities.Vineyard;

import java.io.Serializable;
import java.util.List;

public class ProvisioningCriteria implements Serializable {
    private String name;
    private int year;
    private String producer;
    private String techNotes;
    private List<Vineyard> vineyards;
    private int inQuantity;

    public String getName() {
        return name;
    }

    public ProvisioningCriteria setName(String name) {
        this.name = name;
        return this;
    }

    public int getYear() {
        return year;
    }

    public ProvisioningCriteria setYear(int year) {
        this.year = year;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public ProvisioningCriteria setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public String getTechNotes() {
        return techNotes;
    }

    public ProvisioningCriteria setTechNotes(String techNotes) {
        this.techNotes = techNotes;
        return this;
    }

    public List<Vineyard> getVineyards() {
        return vineyards;
    }

    public ProvisioningCriteria setVineyards(List<Vineyard> vineyards) {
        this.vineyards = vineyards;
        return this;
    }

    public int getInQuantity() {
        return inQuantity;
    }

    public ProvisioningCriteria setInQuantity(int inQuantity) {
        this.inQuantity = inQuantity;
        return this;
    }
}
