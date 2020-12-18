package it.unipr.ingegneria.ui.models.form;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class VineyardInput {
    private VineyardInput(){}
    public static class Field {
        public static final String TITLE = "Vineyard registration";
        public static final Label NAME_LABEL = new Label("Name");
        public static final TextField NAME_INPUT = new TextField();
        //public Field(){};

    }
}
