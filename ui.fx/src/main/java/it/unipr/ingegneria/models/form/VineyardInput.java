package it.unipr.ingegneria.models.form;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class VineyardInput {
    private VineyardInput(){}
    public static class Field {
        public static final String TITLE = "Vineyard registration";
        public static final Label NAME_LABEL = new Label("Name");
        public static final TextField NAME_INPUT = new TextField();
        //public Field(){};

    }
}
