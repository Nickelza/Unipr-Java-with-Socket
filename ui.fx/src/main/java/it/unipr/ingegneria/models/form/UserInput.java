package it.unipr.ingegneria.models.form;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public  class UserInput {
    public static class Field {
        private String title = "User registration";
        public final Label NAME_LABEL = new Label("Name");
        public  final Label SURNAME_LABEL = new Label("Surname");
        public  final Label EMAIL_LABEL = new Label("Email");
        public  final Label PSW_LABEL = new Label("Password");
        public  final  TextField NAME_INPUT= new TextField();
        public  final   TextField SURNAME_INPUT= new TextField();
        public  final   TextField EMAIL_INPUT=new TextField();
        public  final   TextField PSW_INPUT=new TextField();

        public Field(){};

        public Field setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getTitle() {
            return title;
        }

    }
        private UserInput(){};


    }