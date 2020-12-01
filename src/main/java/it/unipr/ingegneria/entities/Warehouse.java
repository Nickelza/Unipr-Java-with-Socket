package it.unipr.ingegneria.entities;

import it.unipr.ingegneria.api.IObservable;
import it.unipr.ingegneria.api.IWarehouseManager;
import it.unipr.ingegneria.exception.AvailabilityException;
import it.unipr.ingegneria.exception.RequiredValueException;

import it.unipr.ingegneria.utils.Params;
import it.unipr.ingegneria.builders.WineBuilder;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * The class {@code Warehouse} defines the behaviour of a Warehouse
 * Implements {@code IWarehouseManager} and {@code IObservable} interfaces.
 *
 * @author Francesca Rossi, Everton Ejike, Ruslan Vasyunin
 * @see it.unipr.ingegneria.api.IWarehouseManager
 * @see it.unipr.ingegneria.api.IObservable
 */
public class Warehouse implements IWarehouseManager<Wine>, IObservable<WineShop> {

    /**
     * Log manager
     */
    private static final Logger logger = Logger.getLogger(Warehouse.class);

    /**
     * Wine shops to observe
     */
    private List<WineShop> observers = new ArrayList<>();

    public Map<String, List<Wine>> getItems() {
        return items;
    }

    private Map<String, List<Wine>> items;

    /**
     * Default class constructor
     */
    public Warehouse() {
        items = new HashMap<>();
    }

    /**
     * Adds a Wine to the Warehouse
     * @param elements
     * @throws RequiredValueException
     */
    public void add(Map<Params, Object> elements) throws RequiredValueException {
        isValidValues(elements);
        String name = (String) elements.get(Params.NAME);
        logger.info("Add in Warehouse Wine");

        if (items.containsKey(name)) {
            List<Wine> wines = items.get(name);
            wines.addAll(buildWines(elements));
            items.replace(name, wines);
        }

        if (!items.containsKey(name))
            items.put(name, buildWines(elements));


        updateSubscribers();
    }

    /**
     * Removes a Wine from the Warehouse
     * @param elements
     * @return {@code List<Wine>}
     * @throws RequiredValueException
     * @throws AvailabilityException
     */
    public List<Wine> remove(Map<Params, Object> elements) throws RequiredValueException, AvailabilityException {
        isValidValues(elements);
        List<Wine> workedWines = new ArrayList<>();
        String name = (String) elements.get(Params.NAME);
        Integer quantity = Integer.parseInt((String) elements.get(Params.QTY));

        logger.info("Check in Warehouse for availability of Wine");

        List<Wine> foundedWines = items.containsKey(name) ? items.get(name) : null;
        int sizes = (foundedWines == null || foundedWines.isEmpty()) ? 0 : foundedWines.size();

        if (sizes == 0 || sizes < quantity)
            throw new AvailabilityException();

        if (foundedWines != null && !foundedWines.isEmpty()) {

            if (quantity <= sizes) {
                logger.info("Wine in Warehouse are available");
                workedWines = foundedWines
                        .stream()
                        .limit(sizes - quantity)
                        .collect(Collectors.toList());
                items.replace(name, workedWines);
            }
        }
        return workedWines;
    }

    /**
     * Gets list of all available wines
     * @return {@code ArrayList<Wine>}
     */
    public List<Wine> getAvailableWines() {
        ArrayList<Wine> allItems = new ArrayList<>();
        items.entrySet().stream().forEach(i -> allItems.addAll(i.getValue()));
        return allItems;

    }

    /**
     * Gets a Wine provided it's name
     * @param name
     * @return {@code Wine}
     */
    public List<Wine> findByName(String name) {

        return this.items.get(name);
    }

    /**
     * Gets a Wine provided it's production year
     * @param year
     * @return {@code ArrayList<Wine>}
     */
    public List<Wine> findByYear(int year) {
        ArrayList<Wine> allItems = new ArrayList<>();
        Iterator<Map.Entry<String, List<Wine>>> iterator = items.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<Wine>> entry = iterator.next();

            allItems.addAll(entry.getValue().stream().filter(i -> workWithDate(i.getYear()) == year)
                    .collect(Collectors.toList()));
        }
        return allItems;
    }

    /**
     * //ToDo: What does this method validate
     * @param elements
     * @throws RequiredValueException
     */
    private void isValidValues(Map<Params, Object> elements) throws RequiredValueException {
        String name = (String) elements.get(Params.NAME);
        // ToDo: RequiredValueException
        if (name == null || name.isEmpty())
            throw new RequiredValueException(Params.NAME.name());

        Integer quantity = Integer.parseInt((String) elements.get(Params.QTY));
        if (quantity == null || quantity < 0)
            throw new RequiredValueException(Params.QTY.name());
    }


    @Override
    public void addObserver(WineShop wineShop) {
        this.observers.add(wineShop);
    }

    @Override
    public void removeObserver(WineShop wineShop) {
        this.observers.remove(wineShop);
    }

    public void updateSubscribers() {
        for (WineShop observer : this.observers) {
            observer.update(null);
        }
    }

    private List<Wine> buildWines(Map<Params, Object> elements) {
        String name = (String) elements.get(Params.NAME);
        Integer number = Integer.parseInt((String) elements.get(Params.QTY));
        Date date = elements.containsKey(Params.DATE) ? (Date) elements.get(Params.DATE) : new Date();
        return IntStream.range(0, number)
                .mapToObj(i ->
                        new WineBuilder()
                                .setId(Long.valueOf(i))
                                .setName(name)
                                .setProducer("")
                                .setYear(date)
                                .setTechNotes("")
                                .build())
                .collect(Collectors.toList());
    }

    private int workWithDate(Date date) {
        return LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(date)).getYear();
    }
}
