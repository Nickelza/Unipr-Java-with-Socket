package it.unipr.ingegneria.models.form;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The {@code SearchWineInput} is a simple class to contains the information of search form
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public  class SearchWineInput {

    /**
     * Static class that contains the field of the search by name
     */
    public static class FieldByName {
        public static final String TITLE = "Search wine by name";
        public static final Label  NAME_LABEL=new Label("Name Wine");
        public static final TextField NAME_INPUT =new TextField();
    }

    /**
     * Static class that contains the field of the search by year
     */
    public static class FieldByYear {
        public static final String TITLE = "Search wine by type";
        public static final Label  YEAR_LABEL=new Label("Year wine");
        public static final TextField YEAR_INPUT =new TextField();

    }

    private SearchWineInput(){};
}
