package it.unipr.ingegneria.ui.models.views;

import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.entities.user.User;
import javafx.scene.control.TableColumn;

import java.util.List;

public class WineData {

    private WineData(){ }
    public static class Coloumns{
        public static final TableColumn WINE_ID = new TableColumn("Id");
        public static final TableColumn NAME = new TableColumn(" Name");
        public static final TableColumn YEAR = new TableColumn("Year");
        public static final TableColumn PRODUCER = new TableColumn("Producer");
        public static final TableColumn TECH_NOTES = new TableColumn("Tech notes");
        public static final TableColumn<Wine, List<Vineyard>> VINEYARDS = new TableColumn("Vineyard");

    }




}
