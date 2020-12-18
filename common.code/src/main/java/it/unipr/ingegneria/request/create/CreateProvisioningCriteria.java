package it.unipr.ingegneria.request.create;

import it.unipr.ingegneria.entities.Vineyard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code CreateProvisioningCriteria} class defines the criteria of request with params for create Provisioning .
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class CreateProvisioningCriteria implements Serializable {
    private String name;
    private int year;
    private String producer;
    private String techNotes;
    private List<Vineyard> vineyards;
    private int inQuantity;


    public CreateProvisioningCriteria() {
        this.vineyards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public CreateProvisioningCriteria setName(String name) {
        this.name = name;
        return this;
    }

    public int getYear() {
        return year;
    }

    public CreateProvisioningCriteria setYear(int year) {
        this.year = year;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public CreateProvisioningCriteria setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public String getTechNotes() {
        return techNotes;
    }

    public CreateProvisioningCriteria setTechNotes(String techNotes) {
        this.techNotes = techNotes;
        return this;
    }

    public List<Vineyard> getVineyards() {
        return vineyards;
    }

    public CreateProvisioningCriteria setVineyards(List<Vineyard> vineyards) {
        this.vineyards = vineyards;
        return this;
    }

    public CreateProvisioningCriteria setVineyard(Vineyard vineyard) {
        this.vineyards.add(vineyard);
        return this;
    }

    public int getInQuantity() {
        return inQuantity;
    }

    public CreateProvisioningCriteria setInQuantity(int inQuantity) {
        this.inQuantity = inQuantity;
        return this;
    }
}
