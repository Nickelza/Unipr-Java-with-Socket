package it.unipr.ingegneria.models.utils;

import javafx.scene.text.Text;

public class Response {
    private Response(){}
    public static class Field {
        private String title;
        private  Text message;

        public Field setTitle(String title) {
            this.title = title;
            return this;
        }

        public Field setMessage(String message) {
            this.message.setText(message);
            return this;
        }

        public String getTitle() {
            return title;
        }

        public Text getMessage() {
            return message;
        }

        public Field(){
            this.message =new Text();
        };

    }
}
