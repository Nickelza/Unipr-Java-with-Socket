package it.unipr.ingegneria.ui.models.form;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public  class LoginInput {
    public static class Field {
        public static final String TITLE = "Login";
        public static final Label EMAIL_LABEL =new Label("Email");
        public static final Label PSW_LABEL =new Label("Password");
        public static final TextField EMAIL_INPUT =new TextField();
        public static final TextField PSW_INPUT=new TextField();
    }
    private LoginInput(){};


}
