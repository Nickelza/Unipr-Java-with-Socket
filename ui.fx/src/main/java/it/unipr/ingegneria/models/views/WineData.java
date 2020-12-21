package it.unipr.ingegneria.models.views;

import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.entities.Wine;
import javafx.scene.control.TableColumn;
import java.util.List;

/**
 * The {@code WineData} is a simple class to rappresentation the output data of the wine
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class WineData {

    private WineData(){ }

    /**
     * The {@code Coloumns} is a static class that contains the coloumns name of the wine table
     *
     * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
     */
    public static class Coloumns{
        public static final TableColumn WINE_ID = new TableColumn("Id");
        public static final TableColumn NAME = new TableColumn(" Name");
        public static final TableColumn YEAR = new TableColumn("Year");
        public static final TableColumn PRODUCER = new TableColumn("Producer");
        public static final TableColumn TECH_NOTES = new TableColumn("Tech notes");
        public static final TableColumn<Wine, List<Vineyard>> VINEYARDS = new TableColumn("Vineyard");
    }
}
