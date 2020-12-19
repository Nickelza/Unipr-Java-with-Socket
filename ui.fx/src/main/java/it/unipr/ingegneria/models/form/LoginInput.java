package it.unipr.ingegneria.models.form;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public  class LoginInput {
    public static class Field {
        public static final String TITLE = "Login";
        public static final Label EMAIL_LABEL =new Label("Email");
        public static final Label PSW_LABEL =new Label("Password");
        public static final TextField EMAIL_INPUT =new TextField();
        public static final PasswordField PSW_INPUT=new PasswordField();
    }
    private LoginInput(){};


}
