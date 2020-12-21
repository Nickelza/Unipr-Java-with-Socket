package it.unipr.ingegneria.models.views;

import javafx.scene.control.TableColumn;

/**
 * The {@code VineyardData} is a simple class to rappresentation the output data of the vineyard
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class VineyardData {

    private VineyardData(){ }

    /**
     * The {@code Coloumns} is a static class that contains the coloumns name of the vineyard table
     *
     * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
     */
    public static class Coloumns{
        public static final TableColumn NAME = new TableColumn("Name");
    }
}
