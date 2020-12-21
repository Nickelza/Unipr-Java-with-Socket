package it.unipr.ingegneria.models.form;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The {@code VineyardInput} is a simple class to contains the information of vineyard form
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class VineyardInput {
    private VineyardInput(){}
    /**
     * The {@code Field} is a static class that contains the field of vineyard form
     *
     * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
     */
    public static class Field {
        public static final String TITLE = "Vineyard registration";
        public static final Label NAME_LABEL = new Label("Name");
        public static final TextField NAME_INPUT = new TextField();
    }
}
