package it.unipr.ingegneria.impl;

import it.unipr.ingegneria.api.IObservable;
import it.unipr.ingegneria.api.IWarehouseManager;
import it.unipr.ingegneria.db.persistance.WarehouseDAO;
import it.unipr.ingegneria.db.persistance.WineDAO;
import it.unipr.ingegneria.db.persistance.relations.RelWineVineyard;
import it.unipr.ingegneria.db.persistance.relations.RelWineWarehouse;
import it.unipr.ingegneria.entities.AbstractWarehouse;
import it.unipr.ingegneria.entities.Shop;
import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.exception.AvailabilityException;
import it.unipr.ingegneria.exception.RequiredValueException;
import it.unipr.ingegneria.utils.Params;
import org.apache.log4j.Logger;

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
public class Warehouse extends AbstractWarehouse implements IWarehouseManager<Wine>, IObservable<Shop> {

    private transient RelWineWarehouse wineWarehouse = RelWineWarehouse.getInstance();
    private transient RelWineVineyard relWineVineyard = RelWineVineyard.getInstance();
    private transient WarehouseDAO warehouseDAO = WarehouseDAO.getInstance();
    private transient WineDAO wineDAO = WineDAO.getInstance();
    private List<Shop> observers = new ArrayList<Shop>();

    private static final Logger LOGGER = Logger.getLogger(Warehouse.class);


    public Warehouse(String name) {
        super(name);
    }


    /**
     * Add a new wine into Warehouse and create a relation record beetwen the generated Wine object and the current Warehouse
     *
     * @param elements Map that contains info about Wine as name and quantity
     * @throws RequiredValueException Exception launched when Wine is not available
     */
    public void add(Map<Params, Object> elements) throws RequiredValueException {
        LOGGER.info("Add in Warehouse Wine");
        isValidValues(elements);

        Integer NUMBER = (Integer) elements.get(Params.QTY);
        Integer WINE_YEAR = elements.containsKey(Params.YEAR) ? (Integer) elements.get(Params.YEAR) : 2020;
        String WINE_NAME = elements.containsKey(Params.NAME) ? (String) elements.get(Params.NAME) : "Lambrusco";
        String WINE_PRODUCER = elements.containsKey(Params.PRODUCER) ? (String) elements.get(Params.PRODUCER) : "Unknown";
        String TECH_NOTES = elements.containsKey(Params.TECH_NOTES) ? (String) elements.get(Params.TECH_NOTES) : "";

        List<Vineyard> VINEYEARDS = elements.containsKey(Params.VINEYARDS) ? (List) elements.get(Params.VINEYARDS) : new ArrayList<>();

        // Cannot use the executeBatch because it's not return the inserted id, so the Wine object is persisted one by one
        List<Wine> wines = IntStream.range(0, NUMBER)
                .mapToObj(i -> new Wine()
                        .setName(WINE_NAME)
                        .setProducer(WINE_PRODUCER)
                        .setYear(WINE_YEAR)
                        .setTechNotes(TECH_NOTES)
                        .setVineyards(VINEYEARDS))
                .map(this::save)
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
        isValidValues(elements);
        List<Wine> workedWines = null;

        String NAME = (String) elements.get(Params.NAME);
        Integer QUANTITY = (Integer) elements.get(Params.QTY);

        LOGGER.info("Check in Warehouse for availability of Wine");


        if (!this.warehouseDAO.checkAvailability(NAME, QUANTITY))
            throw new AvailabilityException();

        List<Wine> foundedWines = this.wineDAO.findByName(NAME);
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
    public List<Wine> findAllAvailables() {
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
    public void addObserver(Shop wineShop) {
        this.observers.add(wineShop);
    }


    /**
     * Remove a wineshop by the observer list
     *
     * @param wineShop
     */
    @Override
    public void removeObserver(Shop wineShop) {
        this.observers.remove(wineShop);
    }

    /**
     * Update the state of the Wines in warehouse
     */
    public void updateSubscribers() {
        for (Shop observer : this.observers) {
            ((WineShop) observer).update(null);
        }
    }


    /**
     * Check in current Warehouse the wines that are finished and if founded notify the ProvisioningManager
     */
    @Override
    public void checkAvailability() {

        List<String> winesNotAvailable = this.warehouseDAO.findWineNotInWarehouse();
        Map<Params, Object> elements = new HashMap<>();
        if (winesNotAvailable != null && !winesNotAvailable.isEmpty()) {
            for (String wineName : winesNotAvailable) {
                elements.put(Params.NAME, wineName);
                elements.put(Params.QTY, 15);
                this.provisioningManager.handleProvisioning(elements);
            }

        }
    }


    /**
     * Check if the params are present and are correct
     *
     * @param elements
     * @throws RequiredValueException
     */
    private void isValidValues(Map<Params, Object> elements) throws RequiredValueException {
        String name = (String) elements.get(Params.NAME);
        if (name == null || name.isEmpty())
            throw new RequiredValueException(Params.NAME.name());

        Integer quantity = (Integer) elements.get(Params.QTY);
        if (quantity == null || quantity < 0)
            throw new RequiredValueException(Params.QTY.name());
    }

    private Wine save(Wine wine) {
        wineDAO.add(wine);
        return wine;
    }


}
