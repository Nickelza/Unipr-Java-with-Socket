package it.unipr.ingegneria.models.form;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The {@code OrderWineInput} is a simple class to contains the information of order form
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public  class OrderWineInput {
    /**
     * The {@code Field} is a static class that contains the field of order wine form
     *
     * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
     */
    public static class Field {
        public static  final String TITLE = "Order Wine";
        public static final Label WINE_LABEL = new Label("Wine");
        public static final Label QTY_LABEL = new Label("Quantity");
        public static final TextField WINE_INPUT = new TextField();
        public static final  TextField QTY_INPUT= new TextField();
    }
        private OrderWineInput(){};
    }