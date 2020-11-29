package it.unipr.ingegneria.entities;

import it.unipr.ingegneria.api.IObservable;
import it.unipr.ingegneria.api.IWarehouseManager;
import it.unipr.ingegneria.api.Persistable;
import it.unipr.ingegneria.db.persistance.WarehouseDAO;
import it.unipr.ingegneria.db.persistance.WineDAO;
import it.unipr.ingegneria.db.persistance.relations.RelWineVineyard;
import it.unipr.ingegneria.db.persistance.relations.RelWineWarehouse;
import it.unipr.ingegneria.exception.AvailabilityException;
import it.unipr.ingegneria.exception.RequiredValueException;
import it.unipr.ingegneria.utils.Params;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The {@code Warehouse} is a class used to manage the stocks of the wine.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class Warehouse implements IWarehouseManager<Wine>, IObservable<WineShop>, Serializable, Persistable<Warehouse> {

    private transient RelWineWarehouse wineWarehouse = RelWineWarehouse.getInstance();
    private transient RelWineVineyard relWineVineyard = RelWineVineyard.getInstance();
    private transient WarehouseDAO warehouseDAO = WarehouseDAO.getInstance();
    private transient WineDAO wineDAO = WineDAO.getInstance();

    private Integer id;
    private String name;
    private List<Wine> items;


    private List<WineShop> observers = new ArrayList<>();
    private ProvisioningManager provisioningManager;
    private static final Logger LOGGER = Logger.getLogger(Warehouse.class);


    public Warehouse(String name) {
        this.name = name;
        this.items = new ArrayList<>();
        this.provisioningManager = new ProvisioningManager();
    }


    /**
     * Add a new wine into Warehouse and create a relation record beetwen the generated Wine object and the current Warehouse
     *
     * @param elements Map that contains info about Wine as name and quantity
     * @throws RequiredValueException Exception launched when Wine is not available
     */
    public void add(Map<Params, Object> elements) {
        LOGGER.info("Add in Warehouse Wine");


        Integer NUMBER = (Integer) elements.get(Params.QTY);
        Integer WINE_YEAR = elements.containsKey(Params.YEAR) ? (Integer) elements.get(Params.YEAR) : 2020;
        String WINE_NAME = elements.containsKey(Params.NAME) ? (String) elements.get(Params.NAME) : "Lambrusco";
        String WINE_PRODUCER = elements.containsKey(Params.PRODUCER) ? (String) elements.get(Params.PRODUCER) : "Unknown";
        String TECH_NOTES = elements.containsKey(Params.TECH_NOTES) ? (String) elements.get(Params.TECH_NOTES) : "";

        List<Vineyard> VINEYEARDS = (List) elements.get(Params.VINEYARDS);

        // Cannot use the executeBatch because it's not return the inserted id, so the Wine object is persisted one by one
        List<Wine> wines = IntStream.range(0, NUMBER)
                .mapToObj(i -> new Wine()
                        .setName(WINE_NAME)
                        .setProducer(WINE_PRODUCER)
                        .setYear(WINE_YEAR)
                        .setTechNotes(TECH_NOTES)
                        .setVineyards(VINEYEARDS)
                        .persist())
                .collect(Collectors.toList());

        // Bind the Wines to current Warehouse
        wineWarehouse.addAll(wines, this);

        // Bind the Wines and Vineyard
        relWineVineyard.addAll(wines);

        updateSubscribers();
    }


    /**
     * Remove the wine and return the Wines if the number is less  or equal to the required quantity
     *
     * @param elements Map that contains info about Wine as name and quantity
     * @return List of Wine
     * @throws RequiredValueException Exception launched when params are not valid
     * @throws AvailabilityException  Exception launched when Wine is not available
     */

    public List<Wine> remove(Map<Params, Object> elements) throws RequiredValueException, AvailabilityException {

        List<Wine> workedWines = null;

        String NAME = (String) elements.get(Params.NAME);
        Integer QUANTITY = (Integer) elements.get(Params.QTY);

        LOGGER.info("Check in Warehouse for availability of Wine");


        if (!this.warehouseDAO.checkAvailability(NAME, QUANTITY))
            throw new AvailabilityException();

        List<Wine> foundedWines = this.warehouseDAO.findByName(NAME);
        if (foundedWines != null && !foundedWines.isEmpty()) {

            workedWines = foundedWines
                    .stream()
                    .limit(QUANTITY)
                    .collect(Collectors.toList());
            wineWarehouse.deleteAll(workedWines, this);
        }

        return workedWines;
    }


    /**
     * Return all the wine available in Warehouse
     *
     * @return List of Wine
     */
    public List<Wine> getAvailableWines() {
        return warehouseDAO.findAll();
    }

    /**
     * Research a wine by name in Wine Table
     *
     * @param name name
     * @return List of Wine
     */
    public List<Wine> findByName(String name) {
        return wineDAO.findByName(name);
    }

    /**
     * Research a wine by year in Wine Table
     *
     * @param year year
     * @return List of Wine
     */
    public List<Wine> findByYear(int year) {
        return wineDAO.findByYear(year);
    }


    /**
     * Research all wines  in Wine Table
     */
    @Override
    public List<Wine> findAll() {
        return wineDAO.findAll();
    }

    /**
     * Add a wineshop into observer list
     *
     * @param wineShop
     */
    @Override
    public void addObserver(WineShop wineShop) {
        this.observers.add(wineShop);
    }


    /**
     * Remove a wineshop by the observer list
     *
     * @param wineShop
     */
    @Override
    public void removeObserver(WineShop wineShop) {
        this.observers.remove(wineShop);
    }

    /**
     * Update the state of the Wines in warehouse
     */
    public void updateSubscribers() {
        for (WineShop observer : this.observers) {
            observer.update(null);
        }
    }


    /**
     * Return a Provisioning Manager assigned to current Warehouse
     *
     * @return ProvisioningManager
     */
    public ProvisioningManager getProvisioningManager() {
        return provisioningManager;
    }


    /**
     * Check in current Warehouse the wines that are finished and if founded notify the ProvisioningManager
     */
    public void checkAvaibility() {

        List<String> winesNotAvaiable = this.warehouseDAO.findWineNotInWarehouse();
        Map<Params, Object> elements = new HashMap<>();
        if (winesNotAvaiable != null && !winesNotAvaiable.isEmpty()) {
            for (String wineName : winesNotAvaiable) {
                elements.put(Params.NAME, wineName);
                elements.put(Params.QTY, 15);
                this.provisioningManager.handleProvisioning(elements);
            }

        }
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
     * Persist the current entity and return itself with id generated by db
     * Return @Warehouse
     * */
    @Override
    public Warehouse persist() {
        warehouseDAO.add(this);
        LOGGER.info("Inserted Warehouse with ID: " + this.id + " in WAREHOUSE Table");
        return this;
    }
}
