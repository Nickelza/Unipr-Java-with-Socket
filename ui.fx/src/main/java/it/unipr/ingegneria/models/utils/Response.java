package it.unipr.ingegneria.models.utils;

import javafx.scene.text.Text;

/**
 * The {@code Response} is a simple class that rappresent the model of a response
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class Response {
    private Response(){}

    /**
     * The {@code Field} is a static class that contains the field of response view
     *
     * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
     */
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
